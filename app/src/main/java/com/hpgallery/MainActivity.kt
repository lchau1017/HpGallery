package com.hpgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hpgallery.feature.details.HpCharacterDetailsScreen
import com.hpgallery.feature.list.HpCharacterListScreen
import com.hpgallery.ui.theme.HpGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Inject AppViewModel using Hilt
    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Observe the theme state from AppViewModel
            val isDarkTheme by appViewModel.isDarkTheme.collectAsState()

            HpGalleryTheme(isDarkTheme) {
                AppNavigation(appViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(
    appViewModel: AppViewModel
) {
    val navController = rememberNavController()
    val isDarkTheme by appViewModel.isDarkTheme.collectAsState()

    NavHost(navController = navController, startDestination = "character_list") {
        composable("character_list") {
            HpCharacterListScreen(onCharacterClick = { characterId ->
                navController.navigate("character_detail/$characterId")
            }, isDarkTheme = isDarkTheme, onToggleTheme = { appViewModel.toggleTheme() })
        }
        composable(
            "character_detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.StringType })
        ) {
            HpCharacterDetailsScreen(onBackClick = { navController.popBackStack() },
                isDarkTheme = isDarkTheme,
                onToggleTheme = { appViewModel.toggleTheme() })
        }
    }
}