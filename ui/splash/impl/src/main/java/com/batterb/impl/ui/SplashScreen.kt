package com.batterb.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batterb.impl.components.SplashAction
import com.batterb.impl.components.SplashSideEffect
import com.batterb.ui.core.common.ext.collectAsEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import com.batterb.ui.core.common.theme.MessengerTheme
import com.batterb.ui.core.common.R

@Composable
fun SplashScreen(
    splashSideEffect: Flow<SplashSideEffect> = emptyFlow(),
    navigateNext: (hasLoggedUser: Boolean) -> Unit = { _ -> },
    dispatchAction: (SplashAction) -> Unit = { }
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        dispatchAction(SplashAction.CheckInitState(context))
    }

    splashSideEffect.collectAsEffect { effect ->
        when (effect) {
            is SplashSideEffect.NavigateByState -> {
                navigateNext(
                    effect.state.hasLoggedUser
                )
            }
        }
    }

    Surface(Modifier.fillMaxSize()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
        }
    }
}

@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SplashScreenDarkPreview() {
    MessengerTheme {
        Surface {
            SplashScreen()
        }

    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun SplashScreenLightPreview() {
    MessengerTheme {
        SplashScreen()
    }
}