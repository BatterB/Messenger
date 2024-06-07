package com.batterb.ui.core.navigation.registry

import cafe.adriel.voyager.core.registry.screenModule
import com.batterb.chat.api.ChatPage
import com.batterb.ui.chat.impl.ChatNavigationScreen

internal val mainPageBindingModule = screenModule {

    register<ChatPage> {
        ChatNavigationScreen(
            chatId = it.chatId,
            chatName = it.chatName,
            chatImage = it.chatImage
        )
    }
}