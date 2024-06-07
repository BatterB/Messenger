package com.batterb.ui.core.navigation

import cafe.adriel.voyager.core.registry.ScreenRegistry
import com.batterb.ui.core.navigation.registry.authScreenBindingModule
import com.batterb.ui.core.navigation.registry.mainPageBindingModule
import com.batterb.ui.core.navigation.registry.splashScreenBindingModule

fun registerNavigationScreens() = ScreenRegistry {
    splashScreenBindingModule()
    authScreenBindingModule()
    mainPageBindingModule()
}