package com.batterb.impl.components

import android.content.Context
import com.batterb.mvi.Action

interface SplashAction : Action {
    data class CheckInitState(val context: Context) : SplashAction
}
