package com.batterb.ui.mainpage.impl.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.batterb.chat.api.ChatPage
import com.batterb.ui.core.common.ext.getScreen
import com.batterb.ui.mainpage.impl.R
import com.batterb.ui.mainpage.impl.components.chatcomponents.ChatItem
import com.batterb.ui.mainpage.impl.components.chatcomponents.ChatList

object ChatsTab : Tab {
    private fun readResolve(): Any = ChatsTab
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.main_page_nav_bar_chat_title)
            val icon = rememberVectorPainter(Icons.Default.Email)

            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        ChatsScreen(
            navigateToChat = {
                navigator.parent?.push(
                    ChatPage(it.name, it.id, com.batterb.ui.core.common.R.drawable.ic_message).getScreen()
                )
            }
        )
    }
}

@Composable
fun ChatsScreen(
    navigateToChat: (chatItem: ChatItem) -> Unit = {}
) {
    val chatItems = remember {
        listOf(
            ChatItem("1", "Alice", "Hey, how are you?", "10:00 AM", "https://example.com/avatar1.jpg"),
            ChatItem("2", "Bob", "See you tomorrow!", "9:45 AM", "https://example.com/avatar2.jpg"),
            // Add more items as needed
        )
    }

    ChatList(chatItems = chatItems) { chatItem ->
        // Handle item click
        navigateToChat(chatItem)
    }
}