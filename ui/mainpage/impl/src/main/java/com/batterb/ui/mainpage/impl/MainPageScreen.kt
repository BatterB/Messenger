package com.batterb.ui.mainpage.impl

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.navigator.tab.TabOptions
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.core.screen.Screen
import com.batterb.ui.mainpage.impl.components.ChatsTab
import com.batterb.ui.mainpage.impl.components.ProfileTab

class MainPageNavigationScreen : Screen {
    @Composable
    override fun Content() {
        MainPageScreen()
    }

}
@Composable
fun MainPageScreen() {

    Surface {
        TabNavigator(ChatsTab) {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar()
                }
            ) { padding ->
                padding.calculateTopPadding()
                CurrentTab()
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    val tabNavigator = LocalTabNavigator.current
    val currentTab = tabNavigator.current

    NavigationBar {
        NavigationBarItem(
            selected = currentTab == ChatsTab,
            onClick = { tabNavigator.current = ChatsTab },
            icon = {
                ChatsTab.options.icon?.let { icon ->
                    Icon(
                        painter = icon,
                        contentDescription = ChatsTab.options.title
                    )
                } ?: Icon(imageVector = Icons.Default.Email, contentDescription = "Чаты")
            },
            label = { Text(ChatsTab.options.title) }
        )
        NavigationBarItem(
            selected = currentTab == ProfileTab,
            onClick = { tabNavigator.current = ProfileTab },
            icon = {
                ProfileTab.options.icon?.let { icon ->
                    Icon(
                        painter = icon,
                        contentDescription = ProfileTab.options.title
                    )
                } ?: Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = { Text(ProfileTab.options.title) }
        )
    }
}


