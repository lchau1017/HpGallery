package com.hpgallery.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.sp
import com.hpgallgery.ui.R

@Immutable
data class HpTypography(
    val headingPrimaryLarge: TextStyle = TextStyle(),
    val headingPrimarySmall: TextStyle = TextStyle(),
    val headingSecondaryLarge: TextStyle = TextStyle(),
    val headingSecondaryMedium: TextStyle = TextStyle(),
    val labelSmall: TextStyle = TextStyle()
)

@Composable
fun hpTypography(colourScheme: ColourScheme): HpTypography {
    return HpTypography(
        headingPrimaryLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.headline_semibold)),
            fontSize = 28.sp,
            lineHeight = 32.2.sp,
            fontWeight = FontWeight.W600,
            color = colourScheme.textPrimary,
            lineBreak = LineBreak.Heading
        ),
        headingPrimarySmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.headline_semibold)),
            fontSize = 17.sp,
            lineHeight = 19.55.sp,
            fontWeight = FontWeight.W600,
            color = colourScheme.textPrimary,
            lineBreak = LineBreak.Heading
        ),
        headingSecondaryLarge = TextStyle(
            fontFamily = FontFamily(Font(R.font.headline_medium)),
            fontSize = 28.sp,
            lineHeight = 32.2.sp,
            fontWeight = FontWeight.W500,
            color = colourScheme.textPrimary,
            lineBreak = LineBreak.Heading
        ),
        headingSecondaryMedium = TextStyle(
            fontFamily = FontFamily(Font(R.font.headline_medium)),
            fontSize = 24.sp,
            lineHeight = 27.6.sp,
            fontWeight = FontWeight.W500,
            color = colourScheme.textPrimary,
            lineBreak = LineBreak.Heading
        ),

        labelSmall = TextStyle(
            fontFamily = FontFamily(Font(R.font.text_sans_medium)),
            fontSize = 14.sp,
            lineHeight = 18.2.sp,
            fontWeight = FontWeight.W500,
            color = colourScheme.textPrimary,
            lineBreak = LineBreak.Simple
        )
    )
}

val LocalTypography = staticCompositionLocalOf { HpTypography() }
