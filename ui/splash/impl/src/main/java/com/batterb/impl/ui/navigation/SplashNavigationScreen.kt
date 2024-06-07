package com.batterb.impl.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.batterb.impl.ui.SplashScreen
import com.batterb.impl.ui.SplashViewModel
import com.batterb.ui.auth.api.AuthScreen
import com.batterb.ui.core.common.ext.getScreen
import com.batterb.ui.main.api.MainPage

class SplashNavigationScreen : Screen {
    @Composable
    override fun Content() {
        val splashViewModel: SplashViewModel = hiltViewModel()
        val navigator = LocalNavigator.currentOrThrow

        SplashScreen(
            splashSideEffect = splashViewModel.effect,
            dispatchAction = splashViewModel::dispatchAction,
            navigateNext = { hasLoggedUser ->

                val preparedDestination = if (!hasLoggedUser) {
                    AuthScreen
                } else {
                    MainPage
                }

                navigator.replaceAll(preparedDestination.getScreen())
            },
        )
    }

}