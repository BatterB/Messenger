package com.batterb.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.batterb.ui.core.navigation.NavHost
import dagger.hilt.android.AndroidEntryPoint
import com.batterb.ui.core.common.theme.MessengerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessengerTheme {
                Surface {
                    NavHost()
                }
            }
        }
    }
}