package com.batterb.ui.core.common.ext

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectAsEffect(
    context: CoroutineContext = Dispatchers.Main,
    block: suspend CoroutineScope.(T) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        onEach { block(it) }.flowOn(context).launchIn(this)
    }
}