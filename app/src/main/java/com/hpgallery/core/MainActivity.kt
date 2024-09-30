package com.hpgallery.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.hpgallery.core.app.AppViewModel
import com.hpgallery.core.navigation.AppNavigation
import com.hpgallery.ui.theme.HpGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isDarkTheme by appViewModel.isDarkTheme.collectAsState()

            HpGalleryTheme(isDarkTheme) {
                AppNavigation(appViewModel)
            }
        }
    }
}
