package com.batterb.impl.ui.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

interface Destination : ScreenProvider {
    object AuthScreen : Destination
    object MainPage : Destination
}