package com.batterb.ui.auth.impl.ui.views.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batterb.common.ext.EMPTY
import com.batterb.ui.auth.impl.mvi.contracts.AuthorizationAction
import com.batterb.ui.core.common.uikit.MessengerButton
import com.batterb.ui.core.common.uikit.MessengerTextField
import com.batterb.ui.core.common.R

@Composable
fun AuthorizationInput(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Bottom,
    dispatchAction: (action: AuthorizationAction) -> Unit = {},
    loginTextValue: String = String.EMPTY,
    passwordTextValue: String = String.EMPTY,
    isError: Boolean = false,
    isPasswordVisible: Boolean = false
) {
    Column(
        verticalArrangement = verticalArrangement,
        modifier = modifier.requiredWidthIn(200.dp, 400.dp)
    ) {
        EmailTextField(
            loginTextValue = loginTextValue,
            dispatchAction = dispatchAction,
            isError = isError
        )
        PasswordTextField(
            loginTextValue = loginTextValue,
            passwordTextValue = passwordTextValue,
            isPasswordVisible = isPasswordVisible,
            isError = isError,
            dispatchAction = dispatchAction
        )
        LoginButton(
            loginTextValue = loginTextValue,
            passwordTextValue = passwordTextValue,
            dispatchAction = dispatchAction
        )
        LoginTypeDivider()
        QrLogin(dispatchAction)
    }
}

@Composable
private fun EmailTextField(
    loginTextValue: String,
    dispatchAction: (action: AuthorizationAction) -> Unit = {},
    isError: Boolean = false
) {
    val focusManager = LocalFocusManager.current
    MessengerTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        updateValue = { newValue: String ->
            dispatchAction(AuthorizationAction.UpdateLoginField(newValue))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = {
            focusManager.moveFocus(FocusDirection.Down)
        }),
        text = loginTextValue,
        hintText = stringResource(com.batterb.ui.auth.impl.R.string.auth_screen_email_placeholder),
        isError = isError
    )
}

@Composable
private fun PasswordTextField(
    loginTextValue: String,
    passwordTextValue: String,
    isPasswordVisible: Boolean = false,
    isError: Boolean = false,
    dispatchAction: (action: AuthorizationAction) -> Unit = {},
) {
    MessengerTextField(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        updateValue = { newValue: String ->
            dispatchAction(AuthorizationAction.UpdatePasswordField(newValue))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(
            onDone = {
                dispatchAction(
                    AuthorizationAction.Login(
                        username = loginTextValue,
                        password = passwordTextValue
                    )
                )
            }),
        text = passwordTextValue,
        isPassword = true,
        passwordVisible = isPasswordVisible,
        changePasswordVisibility = { passwordVisibility: Boolean ->
            dispatchAction(AuthorizationAction.ChangePasswordVisibility(passwordVisibility))
        },
        hintText = stringResource(com.batterb.ui.auth.impl.R.string.auth_screen_password_placeholder),
        isError = isError
    )
}

@Composable
private fun LoginButton(
    loginTextValue: String,
    passwordTextValue: String,
    dispatchAction: (action: AuthorizationAction) -> Unit
) {
    MessengerButton(
        modifier = Modifier
            .heightIn(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        text = stringResource(com.batterb.ui.auth.impl.R.string.auth_screen_login_button_title),
        color = MaterialTheme.colorScheme.secondary,
        textPadding = 8.dp,
        onButtonClicked = {
            dispatchAction(
                AuthorizationAction.Login(
                    username = loginTextValue,
                    password = passwordTextValue
                )
            )
        },
        isEnabled = (loginTextValue != String.EMPTY && passwordTextValue != String.EMPTY)
    )
}

@Composable
private fun LoginTypeDivider() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primary)
        Text(
            text = stringResource(com.batterb.ui.auth.impl.R.string.auth_screen_overline_qr_auth),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
private fun QrLogin(dispatchAction: (action: AuthorizationAction) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                dispatchAction(AuthorizationAction.ShowQrScanner)
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_qr_code),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(com.batterb.ui.auth.impl.R.string.auth_screen_label_qr_auth),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 4.dp)
        )

    }
}

@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AuthorizationInputDarkPreview() {
    MaterialTheme {
        Surface {
            AuthorizationInput()
        }
    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun AuthorizationInputLightPreview() {
    MaterialTheme {
        AuthorizationInput()
    }
}