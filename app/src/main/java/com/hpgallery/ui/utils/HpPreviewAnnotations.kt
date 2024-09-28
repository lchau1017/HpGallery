package com.hpgallery.ui.utils

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

// Annotation for Light Mode Preview
@Preview(name = "Light Mode", showBackground = true)
annotation class LightModePreview

// Annotation for Dark Mode Preview
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class DarkModePreview

@LightModePreview
@DarkModePreview
annotation class DualModePreview