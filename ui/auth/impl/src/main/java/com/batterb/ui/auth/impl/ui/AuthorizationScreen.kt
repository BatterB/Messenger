package com.batterb.ui.auth.impl.ui

import android.content.res.Configuration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.batterb.common.ext.EMPTY
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationAction
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationSideEffect
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationState
import com.batterb.ui.auth.impl.ui.views.MainAuthView
import com.batterb.ui.core.common.ext.collectAsEffect
import com.batterb.ui.core.common.uikit.LoadingView
import com.batterb.ui.auth.impl.ui.views.QrScanner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun AuthScreen(
    authState: AuthorizationState = AuthorizationState.Default(),
    authSideEffect: Flow<AuthorizationSideEffect> = emptyFlow(),
    dispatchAction: (action: AuthorizationAction) -> Unit = { },
    navigateToTranslations: () -> Unit = {}
) {
    val context = LocalContext.current
    val snackState = remember { SnackbarHostState() }
    authSideEffect.collectAsEffect { effect ->
        when (effect){
            is AuthorizationSideEffect.GoToTranslations ->{
                navigateToTranslations()
            }
            is AuthorizationSideEffect.ShowErrorSnackbar ->{
                snackState.showSnackbar(effect.error)
            }
        }
    }


    when (authState) {
        is AuthorizationState.Default -> {
            MainAuthView(
                appVersion = authState.getAppVersion(),
                dispatchAction = dispatchAction,
                loginTextValue = authState.username.get(context),
                passwordTextValue = authState.password.get(context),
                isPasswordVisible = authState.isPasswordVisible,
                isError = authState.loginError,
                snackState = snackState
            )
        }
        is AuthorizationState.Loading -> {
            LoadingView(message = authState.message?.get(context) ?: String.EMPTY)
        }
        is AuthorizationState.QrScanning -> {
            QrScanner(dispatchAction)
        }
    }
}


@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DarkPreview() {
    MaterialTheme {
        AuthScreen()
    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun LightPreview() {
    MaterialTheme {
        AuthScreen()
    }
}