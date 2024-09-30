package com.hpgallery.ui.component

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.hpgallery.R
import com.hpgallery.ui.theme.LocalColourScheme

@Composable
fun HpFloatingActionButton(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    FloatingActionButton(
        onClick = onToggleTheme,
        containerColor = LocalColourScheme.current.fab
    ) {
        Icon(
            painter = painterResource(
                id = if (isDarkTheme) R.drawable.ic_light_mode else R.drawable.ic_dark_mode
            ),
            contentDescription = "Toggle Theme",
            tint = LocalColourScheme.current.backgroundPrimary
        )
    }
}
