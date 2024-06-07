package com.batterb.ui.auth.impl.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.batterb.ui.auth.impl.mvi.AuthorizationViewModel
import com.batterb.ui.auth.impl.ui.AuthScreen
import com.batterb.ui.main.api.MainPage


class AuthorizationNavigationScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val mainPageScreen = rememberScreen(provider = MainPage)

        val authViewModel: AuthorizationViewModel = hiltViewModel()
        val authUiState by authViewModel.state.collectAsStateWithLifecycle()

        AuthScreen(
            authState = authUiState,
            authSideEffect = authViewModel.effect,
            dispatchAction = authViewModel::dispatchAction,
            navigateToTranslations = {
                navigator.replaceAll(mainPageScreen)
            }
        )
    }

}