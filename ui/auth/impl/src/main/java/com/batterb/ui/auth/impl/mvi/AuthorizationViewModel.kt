package com.batterb.ui.auth.impl.mvi

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.batterb.mvi.MviViewModel
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationAction
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationCommand
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationSideEffect
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationState
import com.batterb.ui.core.common.ext.asVO
import com.batterb.ui.core.common.vo.IStringVO
import com.batterb.ui.core.common.vo.Plain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    //private val repository: AuthorizationRepository,
    //private val userRepository: UserRepository,
    //private val userFactory: UserFactory,
    @ApplicationContext private val appContext: Context
) : MviViewModel<AuthorizationState, AuthorizationSideEffect, AuthorizationAction, AuthorizationCommand>(
    AuthorizationState.Default()
) {

    override suspend fun executeAction(action: AuthorizationAction) {
        when (action) {
            is AuthorizationAction.Login -> {
                try {
                    authRequest(action.username, action.password)
                } catch (e: Throwable) {
                    handleCommand(AuthorizationCommand.ShowErrorSnackbar(IStringVO.Plain("${e.message}")))
                    handleCommand(AuthorizationCommand.MarkErrorFields)
                }
            }
            is AuthorizationAction.UpdateLoginField -> {
                handleCommand(AuthorizationCommand.UpdateLoginField(action.username))
            }
            is AuthorizationAction.UpdatePasswordField -> {
                handleCommand(AuthorizationCommand.UpdatePasswordField(action.password))
            }
            AuthorizationAction.ShowQrScanner -> {
                handleCommand(AuthorizationCommand.ShowQrScanner)
            }
            is AuthorizationAction.QrCodeLogin -> {
                qrLoginRequest(action.data)
            }
            AuthorizationAction.CancelQrScan -> {
                handleCommand(AuthorizationCommand.ShowMainScreen)
            }
            is AuthorizationAction.ChangePasswordVisibility -> {
                handleCommand(AuthorizationCommand.SetPasswordVisibility(!action.passwordVisibility))
            }
        }
    }

    private fun authRequest(username: String, password: String) {
        if (!username.trim().isEmailValid()) {
            throw Throwable("Incorrevt password")
        }

//        repository.authorize(username, password)
//            .onEach { result ->
//                when (result) {
//                    is ActionResult.Loading -> {
//                        handleCommand(AuthorizationCommand.Loading(result.message))
//                    }
//                    is ActionResult.Success -> {
//                        userRepository.saveUser(
//                            user = userFactory.create(
//                                login = username.trim(),
//                                password = password.trim(),
//                                token = result.data,
//                                role = UserRole.SCOUT
//                            )
//                        )
//                        handleCommand(AuthorizationCommand.GoToChats)
//                    }
//                    is ActionResult.Error -> {
//                        handleCommand(AuthorizationCommand.MarkErrorFields)
//                        handleCommand(AuthorizationCommand.ShowErrorSnackbar(result.error.extractScoutError()))
//                    }
//                }
//            }
//            .launchIn(viewModelScope)
    }

    private fun qrLoginRequest(loginData: String) {
        try {
            if (loginData.contains("|||")) {
                val loginDataList = loginData.split("|||")
                authRequest(loginDataList[0], loginDataList[1])
            } else {
                throw Throwable("Incorrect QR")
            }
        } catch (e: Throwable) {
            handleCommand(AuthorizationCommand.MarkErrorFields)
            handleCommand(AuthorizationCommand.ShowErrorSnackbar("${e.message}".asVO()))
        }
    }

    private fun String.isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }


    override fun reduce(
        state: AuthorizationState,
        command: AuthorizationCommand
    ): ReduceResult<AuthorizationState, AuthorizationSideEffect> {
        return when (command) {
            is AuthorizationCommand.ShowErrorSnackbar -> ReduceResult.SendEffect(
                AuthorizationSideEffect.ShowErrorSnackbar(
                    command.errorMessage.get(appContext)
                )
            )

            is AuthorizationCommand.Loading -> ReduceResult.UpdateState(
                AuthorizationState.Loading(command.message)
            )

            is AuthorizationCommand.UpdateLoginField -> ReduceResult.UpdateState(
                onUpdateLoginField(state, command.username)
            )

            is AuthorizationCommand.UpdatePasswordField -> ReduceResult.UpdateState(
                onUpdatePasswordField(state, command.password)
            )

            is AuthorizationCommand.SetPasswordVisibility -> ReduceResult.UpdateState(
                onSetPasswordVisibility(state,command.passwordVisibility)
            )

            AuthorizationCommand.ShowQrScanner -> ReduceResult.UpdateState(
                AuthorizationState.QrScanning
            )

            AuthorizationCommand.ShowMainScreen -> ReduceResult.UpdateState(
                AuthorizationState.Default()
            )

            AuthorizationCommand.GoToChats -> ReduceResult.SendEffect(
                AuthorizationSideEffect.GoToTranslations
            )

            AuthorizationCommand.MarkErrorFields -> ReduceResult.UpdateState(
                onMarkErrorFields(state)
            )

        }
    }

    private fun onUpdateLoginField(state: AuthorizationState, username: String): AuthorizationState {
        return when (state) {
            is AuthorizationState.Default -> state.copy(username = username.asVO())
            else -> AuthorizationState.Default(username = username.asVO())
        }
    }

    private fun onUpdatePasswordField(state: AuthorizationState, password: String): AuthorizationState{
        return when (state) {
            is AuthorizationState.Default -> state.copy(password = password.asVO())
            else -> AuthorizationState.Default(password = password.asVO())
        }
    }

    private fun onSetPasswordVisibility(state: AuthorizationState, passwordVisibility: Boolean) : AuthorizationState{
        return when (state) {
            is AuthorizationState.Default -> state.copy(isPasswordVisible = passwordVisibility)
            else -> AuthorizationState.Default(isPasswordVisible = passwordVisibility)
        }
    }

    private fun onMarkErrorFields(state: AuthorizationState) : AuthorizationState{
        return when (state) {
            is AuthorizationState.Default -> state.copy(loginError = true)
            else -> AuthorizationState.Default(loginError = true)
        }
    }
}
