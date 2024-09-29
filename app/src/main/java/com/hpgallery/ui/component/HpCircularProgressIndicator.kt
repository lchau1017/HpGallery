package com.hpgallery.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.utils.DualModePreview

@Composable
fun HpCircularProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(50.dp),
            color = color
        )
    }
}

@DualModePreview
@Composable
fun HpCircularProgressIndicatorPreview() {
    HpGalleryTheme {
        Box(modifier = Modifier.background(LocalColourScheme.current.backgroundPrimary)) {
            HpCircularProgressIndicator(color = LocalColourScheme.current.indicator)
        }
    }
}