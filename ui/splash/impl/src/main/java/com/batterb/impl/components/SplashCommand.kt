package com.batterb.impl.components

import com.batterb.mvi.Command
import com.batterb.impl.ui.vo.AppStateVO

interface SplashCommand : Command {
    data class NavigateByState(val state: AppStateVO): SplashCommand
}