package com.hpgallery.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.hpgallery.ui.theme.HpTypography
import com.hpgallery.ui.utils.DualModePreview

@Composable
fun HpText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    style: TextStyle = HpTypography.bodyLarge,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style,
        maxLines = maxLines,
        overflow = overflow
    )
}
@DualModePreview
@Composable
fun HpTextPreview() {
    HpText(
        text = "Sample Text", style = MaterialTheme.typography.bodySmall, color = Color.Black
    )
}