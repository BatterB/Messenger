package com.batterb.ui.auth.impl.mvi.contracts

import com.batterb.mvi.Action


sealed interface AuthorizationAction: Action {
    data class Login(val username: String, val password: String): AuthorizationAction
    data class UpdateLoginField(val username: String) : AuthorizationAction
    data class UpdatePasswordField(val password: String) : AuthorizationAction
    data class QrCodeLogin(val data: String): AuthorizationAction
    data class ChangePasswordVisibility(val passwordVisibility: Boolean) : AuthorizationAction
    object CancelQrScan : AuthorizationAction
    object ShowQrScanner : AuthorizationAction
}