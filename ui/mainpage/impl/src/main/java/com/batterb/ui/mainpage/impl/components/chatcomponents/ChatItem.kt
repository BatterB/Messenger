package com.batterb.ui.mainpage.impl.components.chatcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.batterb.ui.core.common.R

data class ChatItem(
    val id: String,
    val name: String,
    val lastMessage: String,
    val timestamp: String,
    val avatarUrl: String
)

@Composable
fun ChatList(chatItems: List<ChatItem>, onItemClick: (ChatItem) -> Unit) {
    LazyColumn(
        Modifier.padding(vertical = 8.dp)
    ) {
        items(chatItems) { chatItem ->
            ChatListItem(chatItem = chatItem, onClick = onItemClick)
        }
    }
}

@Composable
fun ChatListItem(chatItem: ChatItem, onClick: (ChatItem) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick(chatItem) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_message),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = chatItem.name, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                Text(text = chatItem.lastMessage, fontSize = 16.sp, color = MaterialTheme.colorScheme.primary)
            }

            Text(
                text = chatItem.timestamp,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}