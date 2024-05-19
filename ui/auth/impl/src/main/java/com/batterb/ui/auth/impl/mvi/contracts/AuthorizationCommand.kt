package com.batterb.ui.auth.impl.mvi.contracts

import com.batterb.mvi.Command
import com.batterb.ui.core.common.vo.IStringVO

sealed interface AuthorizationCommand: Command {
    data class Loading(val message: IStringVO?) : AuthorizationCommand
    data class ShowErrorSnackbar(val errorMessage: IStringVO): AuthorizationCommand
    data class SetPasswordVisibility(val passwordVisibility: Boolean): AuthorizationCommand
    data class UpdateLoginField(val username: String) : AuthorizationCommand

    data class UpdatePasswordField(val password: String) : AuthorizationCommand
    object MarkErrorFields : AuthorizationCommand
    object ShowQrScanner : AuthorizationCommand
    object ShowMainScreen: AuthorizationCommand
    object GoToChats: AuthorizationCommand
}