package com.batterb.ui.core.common.uikit

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batterb.common.ext.EMPTY
import com.batterb.ui.core.common.R
import com.batterb.ui.core.common.theme.MessengerTheme

@Composable
fun MessengerTextField(
    modifier: Modifier = Modifier,
    text: String = String.EMPTY,
    updateValue: (String) -> Unit = {},
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),
    changePasswordVisibility: (Boolean) -> Unit = {},
    isError: Boolean = false,
    hintText: String = "Enter text"
) {
    OutlinedTextField(
        modifier = modifier.border(
            BorderStroke(
                1.dp,
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.onPrimary,
                        MaterialTheme.colorScheme.primary
                    )
                )
            )
        ),
        value = text,
        onValueChange = { updateValue(it) },
        placeholder = { Text(hintText) },
        singleLine = true,
        isError = isError,
        visualTransformation = if (!isPassword || passwordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = { changePasswordVisibility(passwordVisible) }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible) {
                                R.drawable.ic_outline_visibility_off_24
                            } else {
                                R.drawable.ic_outline_visibility_24
                            }
                        ),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    )
}

@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ScoutTextFieldDarkPreview() {
    MessengerTheme {
        Surface {
            MessengerTextField()
        }
    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun ScoutTextFieldLightPreview() {
    MessengerTheme {
        MessengerTextField()
    }
}