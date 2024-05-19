package com.batterb.ui.auth.impl.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.batterb.common.ext.EMPTY
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationAction
import com.batterb.ui.auth.impl.ui.views.components.AuthorizationInfo
import com.batterb.ui.auth.impl.ui.views.components.AuthorizationInput
import com.batterb.ui.auth.impl.ui.views.components.ErrorSnackbar
import com.batterb.ui.core.common.ext.isLandscapeOrientation
import com.batterb.ui.auth.impl.R

@Composable
fun MainAuthView(
    appVersion: String = String.EMPTY,
    dispatchAction: (action: AuthorizationAction) -> Unit = {},
    loginTextValue: String = String.EMPTY,
    passwordTextValue: String = String.EMPTY,
    isError: Boolean = false,
    isPasswordVisible: Boolean = false,
    snackState: SnackbarHostState = remember { SnackbarHostState() }
){
    val isPhoneHorizontal = isLandscapeOrientation()
    Surface(Modifier.fillMaxSize()){
        Box(Modifier.fillMaxSize()){
            MainAuthBackground(isPhoneHorizontal = isPhoneHorizontal)
            AuthContentView(
                modifier = Modifier.align(Alignment.Center),
                appVersion = appVersion,
                dispatchAction = dispatchAction,
                loginTextValue = loginTextValue,
                passwordTextValue = passwordTextValue,
                isPhoneHorizontal = isPhoneHorizontal,
                isError = isError,
                isPasswordVisible = isPasswordVisible
            )
            SnackbarHost(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(8.dp),
                hostState = snackState
            ) { snackbarData: SnackbarData ->
                ErrorSnackbar(snackbarData.visuals.message)
            }

        }
    }
}

@Composable
fun MainAuthBackground(isPhoneHorizontal: Boolean = false){
    val colors = listOf(MaterialTheme.colorScheme.background, Color.Transparent)
    val imageAlpha = ContentAlpha.medium
    val maxHeight = 1f.takeIf {isPhoneHorizontal } ?: 0.5f
    val maxWidth = 0.5f.takeIf { isPhoneHorizontal } ?: 1f
    val gradient = Brush.horizontalGradient(colors).takeIf { isPhoneHorizontal } ?: Brush.verticalGradient(colors)
    Image(
        painterLoginBackground(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .graphicsLayer { alpha = imageAlpha }
            .drawWithContent {
                drawContent()
                drawRect(
                    brush = gradient,
                    blendMode = BlendMode.DstIn
                )
            }
            .fillMaxHeight(maxHeight)
            .fillMaxWidth(maxWidth)
    )
}

@Composable
fun painterLoginBackground(): Painter {
    return painterResource(id = R.drawable.auth_bg)
}


@Composable
fun AuthContentView(
    modifier: Modifier = Modifier,
    appVersion: String = "0.0.1 ALPHA",
    dispatchAction: (action: AuthorizationAction) -> Unit = {},
    loginTextValue: String = String.EMPTY,
    passwordTextValue: String = String.EMPTY,
    isError: Boolean = false,
    isPhoneHorizontal: Boolean = false,
    isPasswordVisible : Boolean = false
) {
    if (isPhoneHorizontal) {
        Row(modifier, verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            AuthorizationInfo(Modifier.weight(1f))
            AuthorizationInput(
                modifier = Modifier.weight(1f),
                dispatchAction = dispatchAction,
                verticalArrangement = Arrangement.Center,
                loginTextValue = loginTextValue,
                passwordTextValue = passwordTextValue,
                isError = isError,
                isPasswordVisible = isPasswordVisible
            )
        }
    } else {
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            AuthorizationInfo(
                modifier = Modifier.weight(1f),
                appVersion
            )
            AuthorizationInput(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Top,
                dispatchAction = dispatchAction,
                loginTextValue = loginTextValue,
                passwordTextValue = passwordTextValue,
                isError = isError,
                isPasswordVisible = isPasswordVisible
            )
        }
    }
}

@Preview(
    name = "DARK THEME",
    showBackground = true,
    device = Devices.PIXEL_C,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DarkPreviewMainLandscape() {
    MaterialTheme {
        MainAuthView()
    }
}

@Preview(
    name = "LIGHT THEME",
    device = Devices.PIXEL_C,
    showBackground = true,
)
@Composable
fun LightPreviewMainLandscape() {
    MaterialTheme {
        MainAuthView()
    }
}

@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DarkPreviewMainPortrait() {
    MaterialTheme {
        MainAuthView()
    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun LightPreviewMainPortrait() {
    MaterialTheme {
        MainAuthView()
    }
}