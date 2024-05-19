package com.batterb.ui.auth.impl.mvi.contracts

import com.batterb.mvi.UiState
import com.batterb.ui.core.common.vo.EMPTY
import com.batterb.ui.core.common.vo.IStringVO

sealed interface AuthorizationState : UiState {
    data class Default(
        val username: IStringVO = IStringVO.EMPTY,
        val password: IStringVO = IStringVO.EMPTY,
        val isPasswordVisible: Boolean = false,
        val loginError: Boolean = false
    ) : AuthorizationState {
        fun getAppVersion() = "0.0.1 Alpha"
    }

    data class Loading(val message: IStringVO?) : AuthorizationState
    object QrScanning : AuthorizationState
}

