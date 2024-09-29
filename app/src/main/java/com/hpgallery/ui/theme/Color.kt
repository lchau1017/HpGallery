package com.hpgallery.ui.theme

import androidx.compose.ui.graphics.Color

val hp_neutral7 = Color(color = 0xFF20201D)
val hp_neutral20 = Color(color = 0xFF363632)
val hp_neutral38 = Color(color = 0xFF61605C)
val hp_neutral46 = Color(color = 0xFF8A8984)
val hp_neutral60 = Color(color = 0xFFBAB9B3)
val hp_neutral86 = Color(color = 0xFFD6D5D0)
val hp_neutral93 = Color(color = 0xFFF0EFEB)
val hp_neutral97 = Color(color = 0xFFF9F9F5)
val hp_neutral100 = Color(color = 0xFFFAFAFA)
val error = Color(color = 0xFF00FF00)


val purple_80 = Color(0xFFD0BCFF)
val purple_40 = Color(0xFF6650a4)

internal fun Color.transparent10() = copy(alpha = alpha_10.toFloat() / 0xFF.toFloat())
internal fun Color.transparent25() = copy(alpha = alpha_25.toFloat() / 0xFF.toFloat())
internal fun Color.transparent50() = copy(alpha = alpha_50.toFloat() / 0xFF.toFloat())

// House Colors
val GryffindorColor = Color(0xFF740001)
val SlytherinColor = Color(0xFF1A472A)
val RavenclawColor = Color(0xFF0C1A40)
val HufflepuffColor = Color(0xFFEEB939)

// Lighter House colors
val GryffindorLightColor = lightenColor(GryffindorColor, 0.2f)
val SlytherinLightColor = lightenColor(SlytherinColor, 0.2f)
val RavenclawLightColor = lightenColor(RavenclawColor, 0.2f)
val HufflepuffLightColor = lightenColor(HufflepuffColor, 0.2f)
val errorLightColor = lightenColor(error, 0.2f)

private val alpha_10 = 0x1A
private val alpha_20 = 0x33
private val alpha_25 = 0x40
private val alpha_30 = 0x4D
private val alpha_40 = 0x66
private val alpha_50 = 0x80
private val alpha_60 = 0x99
private val alpha_70 = 0xB3
private val alpha_80 = 0xCC
private val alpha_90 = 0xE6

fun lightenColor(color: Color, factor: Float): Color {
    val r = (color.red + (1 - color.red) * factor).coerceIn(0f, 1f)
    val g = (color.green + (1 - color.green) * factor).coerceIn(0f, 1f)
    val b = (color.blue + (1 - color.blue) * factor).coerceIn(0f, 1f)
    return Color(r, g, b)
}