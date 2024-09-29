package com.hpgallery.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ColourScheme(
    val accentPrimary: Color,
    val accentSecondary: Color,
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundTertiary: Color,
    val backgroundQuaternary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val textLink: Color,
    val divider: Color,
    val shadow: Color,
    val fab: Color ,
)

fun lightColourPalette(): ColourScheme = ColourScheme(
    accentPrimary = highlight500,
    accentSecondary = highlight800,
    backgroundPrimary = hp_neutral97,
    backgroundSecondary = hp_neutral93,
    backgroundTertiary = highlight800,
    backgroundQuaternary = highlight200,
    textPrimary = hp_neutral7,
    textSecondary = hp_neutral38,
    textTertiary = hp_neutral97,
    textLink = highlight100,
    divider = hp_neutral7.transparent10(),
    shadow = hp_neutral7.transparent50(),
    fab = hp_neutral7
)

fun darkColourPalette(): ColourScheme = ColourScheme(
    accentPrimary = highlight200,
    accentSecondary = highlight100,
    backgroundPrimary = hp_neutral20,
    backgroundSecondary = hp_neutral7,
    backgroundTertiary = highlight100,
    backgroundQuaternary = highlight400,
    textPrimary = hp_neutral100,
    textSecondary = hp_neutral60,
    textTertiary = hp_neutral7,
    textLink = highlight800,
    divider = hp_neutral38.transparent25(),
    shadow = hp_neutral38.transparent50(),
    fab = hp_neutral100
)

val LocalColourScheme = staticCompositionLocalOf { lightColourPalette() }