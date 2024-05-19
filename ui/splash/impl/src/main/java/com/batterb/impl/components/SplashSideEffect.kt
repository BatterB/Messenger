package com.batterb.impl.components

import com.batterb.impl.ui.vo.AppStateVO
import com.batterb.mvi.SideEffect

interface SplashSideEffect : SideEffect {
    data class NavigateByState(val state: AppStateVO) : SplashSideEffect
}