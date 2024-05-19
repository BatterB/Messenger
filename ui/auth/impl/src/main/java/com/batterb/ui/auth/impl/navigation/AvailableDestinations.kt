package com.batterb.ui.auth.impl.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

sealed interface Destination : ScreenProvider {
    data object Chats : Destination
}