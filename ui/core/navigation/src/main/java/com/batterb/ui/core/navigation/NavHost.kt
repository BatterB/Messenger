package com.batterb.ui.core.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import com.batterb.impl.ui.navigation.SplashNavigationScreen

@Composable
fun NavHost() {
    Navigator(SplashNavigationScreen()) { navigator ->
        FadeTransition(navigator = navigator)
    }
}