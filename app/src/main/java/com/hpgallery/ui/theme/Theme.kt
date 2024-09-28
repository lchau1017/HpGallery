package com.hpgallery.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    onPrimary = Color.White,
    secondary = LightSecondaryColor,
    onSecondary = Color.Black,
    background = LightBackgroundColor,
    surface = LightSurfaceColor,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    onPrimary = Color.Black,
    secondary = DarkSecondaryColor,
    onSecondary = Color.White,
    background = DarkBackgroundColor,
    surface = DarkSurfaceColor,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary
)


@Composable
fun HpGalleryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        shapes = HpShapes,
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}