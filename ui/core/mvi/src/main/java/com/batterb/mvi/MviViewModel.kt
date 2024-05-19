package com.batterb.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow

private const val BUFFER_CAPACITY = 64

@HiltViewModel
abstract class MviViewModel<S : UiState, SE : SideEffect, A : Action, C : Command>(
    initialState: S
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state.asStateFlow()

    private val _effect = Channel<SE>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private val actionFlow = MutableSharedFlow<A>(extraBufferCapacity = BUFFER_CAPACITY)
    private val commandFlow = MutableSharedFlow<C>(extraBufferCapacity = BUFFER_CAPACITY)

    init {
        actionFlow
            .onEach { intent ->
                executeAction(intent)
            }
            .launchIn(viewModelScope)
        commandFlow
            .onEach { command ->
                when (val action = reduce(state.value, command)) {
                    is ReduceResult.UpdateState -> {
                        _state.value = action.state
                    }
                    is ReduceResult.SendEffect -> {
                        _effect.send(action.effect)
                    }
                }

            }
            .launchIn(viewModelScope)
    }

    fun dispatchAction(action: A) {
        actionFlow.tryEmit(action)
    }

    protected fun handleCommand(command: C) {
        commandFlow.tryEmit(command)
    }

    protected abstract suspend fun executeAction(action: A)

    protected abstract fun reduce(state: S, command: C): ReduceResult<S, SE>

    sealed interface ReduceResult<S, SE> {
        data class UpdateState<S, SE>(val state: S) : ReduceResult<S, SE>
        data class SendEffect<S, SE>(val effect: SE) : ReduceResult<S, SE>
    }
}

interface UiState

interface SideEffect

interface Action

interface Command
