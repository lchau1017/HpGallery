package com.hpgallery.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColourScheme(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val divider: Color,
    val shadow: Color,
    val fab: Color,
    val indicator: Color
)

fun lightColourPalette(): ColourScheme = ColourScheme(
    backgroundPrimary = hp_neutral97,
    backgroundSecondary = hp_neutral93,
    textPrimary = hp_neutral7,
    textSecondary = hp_neutral38,
    textTertiary = hp_neutral97,
    divider = hp_neutral7.transparent10(),
    shadow = hp_neutral7.transparent50(),
    fab = hp_neutral7,
    indicator = purple_40
)

fun darkColourPalette(): ColourScheme = ColourScheme(
    backgroundPrimary = hp_neutral20,
    backgroundSecondary = hp_neutral7,
    textPrimary = hp_neutral100,
    textSecondary = hp_neutral60,
    textTertiary = hp_neutral7,
    divider = hp_neutral38.transparent25(),
    shadow = hp_neutral38.transparent50(),
    fab = hp_neutral100,
    indicator = purple_80
)

val LocalColourScheme = staticCompositionLocalOf { lightColourPalette() }