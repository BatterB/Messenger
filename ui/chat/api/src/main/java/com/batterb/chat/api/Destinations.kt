package com.batterb.chat.api

import androidx.annotation.DrawableRes
import com.batterb.ui.Destination

data class ChatPage(
    val chatName: String,
    val chatId: String,
    @DrawableRes val chatImage: Int,
) : Destination