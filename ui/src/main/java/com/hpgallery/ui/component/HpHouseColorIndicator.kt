package com.hpgallery.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hpgallery.ui.DualModePreview
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme

@Composable
fun HpHouseColorIndicator(
    color: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .size(16.dp)
            .background(color, CircleShape),
        shape = CircleShape,
        color = color,
        content = {}
    )
}

@DualModePreview
@Composable
fun HpHouseColorIndicatorPreview() {
    HpGalleryTheme {
        Box(modifier = Modifier.background(LocalColourScheme.current.backgroundPrimary)) {
            HpHouseColorIndicator(color = Color.Red)
        }
    }
}
