package com.batterb.ui.core.navigation.registry

import cafe.adriel.voyager.core.registry.screenModule
import com.batterb.ui.auth.api.AuthScreen
import com.batterb.ui.auth.impl.navigation.AuthorizationNavigationScreen

internal val splashScreenBindingModule = screenModule {

    register<AuthScreen> {
        AuthorizationNavigationScreen()
    }
}