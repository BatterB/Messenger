package com.batterb.impl.ui

import androidx.compose.animation.fadeIn
import com.batterb.impl.components.SplashAction
import com.batterb.impl.components.SplashCommand
import com.batterb.impl.components.SplashSideEffect
import com.batterb.impl.components.SplashState
import com.batterb.impl.ui.vo.AppStateVO
import com.batterb.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
//    private val userRepository: UserRepository
) : MviViewModel<SplashState, SplashSideEffect, SplashAction, SplashCommand>(SplashState) {

    override suspend fun executeAction(action: SplashAction) {
        when (action) {
            is SplashAction.CheckInitState -> {
                val initAppState = AppStateVO(
                    hasLoggedUser = false
                    //userRepository.getUser() != null
                )
                handleCommand(SplashCommand.NavigateByState(initAppState))
            }
        }
    }

    override fun reduce(
        state: SplashState,
        command: SplashCommand
    ): ReduceResult<SplashState, SplashSideEffect> {
        return when (command) {
            is SplashCommand.NavigateByState -> ReduceResult.SendEffect(
                SplashSideEffect.NavigateByState(
                    command.state
                )
            )
            else -> {
                throw IllegalStateException("Command is not correct")
            }
        }

    }
}