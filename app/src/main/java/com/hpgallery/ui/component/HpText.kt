package com.hpgallery.ui.component

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.theme.LocalTypography
import com.hpgallery.ui.utils.DualModePreview

@Composable
fun HpText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    style: TextStyle,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow
    )
}

@DualModePreview
@Composable
fun HpTextPreview() {
    HpGalleryTheme {
        HpText(
            modifier = Modifier.background(LocalColourScheme.current.backgroundPrimary),
            text = "Sample Text",
            style = LocalTypography.current.labelSmall,
            color = LocalColourScheme.current.textPrimary
        )
    }
}
