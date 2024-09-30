package com.hpgallery.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun HpGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colourPalette = if (darkTheme) darkColourPalette() else lightColourPalette()
    val typography = hpTypography(colourPalette)
    CompositionLocalProvider(
        LocalColourScheme provides colourPalette,
        LocalTypography provides typography,
        LocalShapes provides HpShapes,
        content = content
    )
}
