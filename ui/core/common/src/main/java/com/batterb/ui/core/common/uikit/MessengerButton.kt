package com.batterb.ui.core.common.uikit

import android.os.Messenger
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.batterb.ui.core.common.theme.Green
import com.batterb.ui.core.common.theme.MessengerTheme

@Composable
fun MessengerButton(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit = {},
    text: String,
    textPadding: Dp = 0.dp,
    color: Color,
    isEnabled: Boolean = true
) {
    val alpha = if (isEnabled){
        1f
    }else {
        0.3f
    }
    OutlinedButton(
        modifier = modifier,
        onClick = onButtonClicked,
        border = BorderStroke(2.dp, color.copy(alpha = alpha)),
        enabled = isEnabled
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(textPadding),
            text = text,
            style = TextStyle(
                color = color.copy(alpha = alpha),
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
            )
        )
    }
}

@Preview
@Composable
fun ScoutButtonPreview() {
    MessengerTheme {
        Surface {
            MessengerButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "PREVIEW",
                color = Green
            )
        }
    }
}