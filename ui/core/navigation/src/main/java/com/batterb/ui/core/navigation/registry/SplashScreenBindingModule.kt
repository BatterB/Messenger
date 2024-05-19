package com.batterb.ui.core.navigation.registry

import cafe.adriel.voyager.core.registry.screenModule
import com.batterb.impl.ui.navigation.Destination
import com.batterb.ui.auth.impl.navigation.AuthorizationNavigationScreen

internal val splashScreenBindingModule = screenModule {

    register<Destination.AuthScreen> {
        AuthorizationNavigationScreen()
    }
}