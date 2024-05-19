package com.batterb.ui.auth.impl.ui.views.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.batterb.ui.auth.impl.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrScannerTopBar(
    modifier: Modifier = Modifier,
    titleText: String = stringResource(id = R.string.qr_screen_qr_scanner_header),
    onExitClicked: () -> Unit = { }
) {
    Column {
        TopAppBar(
            modifier = modifier,
            title = {
                Text(
                    text = titleText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = onExitClicked) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "backIcon")
                }
            }
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TopBarPreview() {
    MaterialTheme {
        QrScannerTopBar()
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarDayPreview() {
    MaterialTheme {
        QrScannerTopBar()
    }
}