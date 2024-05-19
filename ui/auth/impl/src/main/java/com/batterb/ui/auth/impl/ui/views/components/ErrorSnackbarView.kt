package com.batterb.ui.auth.impl.ui.views.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.batterb.common.ext.EMPTY

@Composable
fun ErrorSnackbar(error: String = String.EMPTY){
    Snackbar(
        modifier = Modifier.fillMaxWidth(0.3f),
        containerColor = MaterialTheme.colorScheme.error,
        shape = RoundedCornerShape(64.dp)
    ) {
        Text(text = error, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}


@Preview(
    name = "DARK THEME",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ErrorSnackbarDarkPreview() {
    MaterialTheme {
        Surface(Modifier.fillMaxWidth()) {
            ErrorSnackbar("Some troubles\nU01 : Connection lost")
        }
    }
}

@Preview(
    name = "LIGHT THEME",
    showBackground = true,
)
@Composable
fun ErrorSnackbarLightPreview() {
    MaterialTheme {
        Surface(Modifier.fillMaxWidth()) {
            ErrorSnackbar("Some troubles\nU01 : Connection lost")
        }
    }
}