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
    val error: Color,
    val fab: Color,
    val indicator: Color,
    val gryffindor: Color,
    val slytherin: Color,
    val ravenclaw: Color,
    val hufflepuff: Color,
)

fun lightColourPalette(): ColourScheme = ColourScheme(
    backgroundPrimary = hp_neutral97,
    backgroundSecondary = hp_neutral93,
    textPrimary = hp_neutral7,
    textSecondary = hp_neutral38,
    textTertiary = hp_neutral97,
    divider = hp_neutral7.transparent10(),
    shadow = hp_neutral7.transparent50(),
    error = error,
    fab = hp_neutral7,
    indicator = purple_40,
    gryffindor = GryffindorColor,
    slytherin = SlytherinColor,
    ravenclaw = RavenclawColor,
    hufflepuff = HufflepuffColor,
)

fun darkColourPalette(): ColourScheme = ColourScheme(
    backgroundPrimary = hp_neutral20,
    backgroundSecondary = hp_neutral7,
    textPrimary = hp_neutral100,
    textSecondary = hp_neutral60,
    textTertiary = hp_neutral7,
    divider = hp_neutral38.transparent25(),
    shadow = hp_neutral38.transparent50(),
    error = error,
    fab = hp_neutral100,
    indicator = purple_80,
    gryffindor = GryffindorLightColor,
    slytherin = SlytherinLightColor,
    ravenclaw = RavenclawLightColor,
    hufflepuff = HufflepuffLightColor,
)

val LocalColourScheme = staticCompositionLocalOf { lightColourPalette() }