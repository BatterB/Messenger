package com.batterb.impl.ui.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.hilt.getViewModel
import com.batterb.impl.ui.SplashScreen
import com.batterb.impl.ui.SplashViewModel
import com.batterb.ui.core.common.ext.getScreen

class SplashNavigationScreen : Screen {
    @Composable
    override fun Content() {
        val splashViewModel: SplashViewModel = getViewModel()
        val navigator = LocalNavigator.currentOrThrow

        SplashScreen(
            splashSideEffect = splashViewModel.effect,
            dispatchAction = splashViewModel::dispatchAction,
            navigateNext = { hasLoggedUser ->

                val preparedDestination = if (hasLoggedUser) {
                    Destination.MainPage
                } else {
                    Destination.AuthScreen
                }

                navigator.replaceAll(preparedDestination.getScreen())
            },
        )
    }

}