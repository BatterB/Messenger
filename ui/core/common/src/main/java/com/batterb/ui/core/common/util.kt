package com.batterb.ui.core.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

val LocalActivityLifecycleOwner = staticCompositionLocalOf<LifecycleOwner> {
    error("LocalActivityLifecycleOwner not implemented")
}

@Composable
fun ActivityLifecycleProvider(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalActivityLifecycleOwner provides LocalLifecycleOwner.current,
        content = content
    )
}

@Composable
fun LifecycleObserver(
    onCreate: LifecycleOwner.() -> Unit = { },
    onStart: LifecycleOwner.() -> Unit = { },
    onResume: LifecycleOwner.() -> Unit = { },
    onPause: LifecycleOwner.() -> Unit = { },
    onStop: LifecycleOwner.() -> Unit = { },
    onDestroy: LifecycleOwner.() -> Unit = { }
) {

    val lifecycleOwner = LocalActivityLifecycleOwner.current
    val lifecycle = lifecycleOwner.lifecycle
    DisposableEffect(lifecycle) {
        val observer = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) = onCreate(owner)
            override fun onStart(owner: LifecycleOwner) = onStart(owner)
            override fun onResume(owner: LifecycleOwner) = onResume(owner)
            override fun onPause(owner: LifecycleOwner) = onPause(owner)
            override fun onStop(owner: LifecycleOwner) = onStop(owner)
            override fun onDestroy(owner: LifecycleOwner) = onDestroy(owner)
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}