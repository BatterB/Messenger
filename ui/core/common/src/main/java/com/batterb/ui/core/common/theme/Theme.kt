package com.batterb.ui.core.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.batterb.ui.core.common.ActivityLifecycleProvider

private val darkColorPalette = darkColorScheme(
    primary = Accent,
    onPrimary = AccentLight,
    secondary = LightPurple,
    error = Red
)

private val lightColorPalette = lightColorScheme()

@Composable
fun MessengerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorScheme = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    ActivityLifecycleProvider {
        LocalPaddingProvider {
            MaterialTheme(
                colorScheme = colorScheme,
                typography = Typography,
                shapes = Shapes,
                content = content
            )
        }
    }
}