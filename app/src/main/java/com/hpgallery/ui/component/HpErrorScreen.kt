package com.hpgallery.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.theme.LocalTypography


@Composable
fun HpErrorScreen(
    errorMessage: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp), contentAlignment = Alignment.Center
    ) {
        HpText(
            textAlign = TextAlign.Center,
            text = errorMessage,
            style = LocalTypography.current.headingPrimaryLarge,
            color = LocalColourScheme.current.error,
        )
    }
}