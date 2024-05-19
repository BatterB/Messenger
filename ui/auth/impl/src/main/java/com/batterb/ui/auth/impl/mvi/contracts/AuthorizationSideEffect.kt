package com.batterb.ui.auth.impl.mvi.contracts

import com.batterb.mvi.SideEffect

sealed interface AuthorizationSideEffect: SideEffect {
    object GoToTranslations : AuthorizationSideEffect
    data class ShowErrorSnackbar(val error: String): AuthorizationSideEffect
}