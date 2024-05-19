package com.batterb.ui.core.common.uikit

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batterb.common.ext.EMPTY

@Composable
fun LoadingView(
    modifier: Modifier = Modifier,
    type: LoaderType = LoaderType.Infinite,
    message: String = String.EMPTY
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (type) {
            LoaderType.Infinite -> {
                CircularProgressIndicator()
            }
            is LoaderType.Percentage -> {
                CircularProgressIndicator(
                    progress = { type.progress },
                )
            }
        }
        val shouldShowMessage = message.isNotBlank()
        val spacedHeight by animateDpAsState(targetValue = if (shouldShowMessage) 16.dp else 0.dp)
        Spacer(modifier = Modifier.height(spacedHeight))

        AnimatedVisibility(visible = shouldShowMessage) {
            Text(text = message)
        }
    }
}

sealed interface LoaderType {
    object Infinite : LoaderType
    data class Percentage(val progress: Float) : LoaderType
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoadingViewPrev() {
    MaterialTheme {
        LoadingView(
            message = "some common message"
        )
    }
}