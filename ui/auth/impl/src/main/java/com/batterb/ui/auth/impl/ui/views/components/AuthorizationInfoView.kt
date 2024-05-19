package com.batterb.ui.auth.impl.ui.views.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.batterb.ui.core.common.theme.LogoTitle
import com.batterb.ui.core.common.theme.MessengerTheme
import com.batterb.ui.core.common.R

@Composable
fun AuthorizationInfo(
    modifier: Modifier = Modifier,
    appVersion : String = ""
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_message),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(text = stringResource(id = com.batterb.ui.auth.impl.R.string.auth_screen_logo_title), style = MaterialTheme.typography.LogoTitle)
        Text(
            text = appVersion,
            fontSize = 20.sp
        )
    }

}

@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AuthorizationInfoDarkPreview() {
    MessengerTheme {
        Surface {
            AuthorizationInfo()
        }

    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun AuthorizationInfoLightPreview() {
    MessengerTheme {
        AuthorizationInfo()
    }
}