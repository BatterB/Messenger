package com.batterb.ui.chat.impl

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.batterb.ui.chat.impl.components.ChatHeader
import com.batterb.ui.chat.impl.components.MessageInput
import com.batterb.ui.chat.impl.components.MessageItem

class ChatNavigationScreen(
    val chatName: String,
    val chatId: String,
    @DrawableRes val chatImage: Int
) : Screen {
    @Composable
    override fun Content() {
        val messages = remember { mutableStateListOf<Pair<String, Boolean>>() }
        val navigator = LocalNavigator.currentOrThrow

        ChatScreen(
            chatName = chatName,
            chatImage = chatImage,
            messages = messages,
            onBackClick = { navigator.pop() },
            onMessageSent = { message ->
                messages.add(Pair(message, true))
            }
        )
    }
}
@Composable
fun ChatScreen(
    chatName: String,
    @DrawableRes chatImage: Int,
    messages: List<Pair<String, Boolean>>, // Pair of message and isUserMessage flag
    onBackClick: () -> Unit,
    onMessageSent: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ChatHeader(chatName = chatName, chatImage = chatImage, onBackClick = onBackClick)

        Divider(color = Color.LightGray, thickness = 1.dp)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            messages.forEach { (message, isUserMessage) ->
                MessageItem(message = message, isUserMessage = isUserMessage)
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)

        MessageInput(onMessageSent = onMessageSent)
    }
}