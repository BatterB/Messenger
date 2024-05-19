package com.batterb.ui.core.common.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Padding(

    /**
     * Tiny padding. By default 4 [dp]
     * */
    val tiny: Dp = 4.dp,

    /**
     * Small padding. By default 8 [dp]
     * */
    val small: Dp = 8.dp,

    /**
     * Medium padding. By default 12 [dp]
     * */
    val medium: Dp = 12.dp,

    /**
     * Common padding. By default 16 [dp]
     * */
    val common: Dp = 16.dp,

    /**
     * Big padding. By default 24 [dp]
     * */
    val big: Dp = 24.dp,

    /**
     * Large padding. By default 32 [dp]
     * */
    val large: Dp = 32.dp,

    /*
    /**
     * Extra large padding. By default 64 [dp]
     * */
    val extraLarge: Dp = 64.dp,
     */
)

val LocalPadding = staticCompositionLocalOf<Padding> {
    error("LocalPadding value not provided")
}

@Composable
fun LocalPaddingProvider(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalPadding provides Padding(),
        content = content
    )
}

/**
 * Provides standard kit of available app paddings.
 *
 * Recommended way to use paddings.
 * */
val MaterialTheme.padding
    @Composable
    @ReadOnlyComposable
    get() = LocalPadding.current