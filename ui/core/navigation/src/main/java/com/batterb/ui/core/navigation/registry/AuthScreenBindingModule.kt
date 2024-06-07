package com.batterb.ui.core.navigation.registry

import cafe.adriel.voyager.core.registry.screenModule
import com.batterb.ui.main.api.MainPage
import com.batterb.ui.mainpage.impl.MainPageNavigationScreen

internal val authScreenBindingModule = screenModule {

    register<MainPage> {
        MainPageNavigationScreen()
    }
}