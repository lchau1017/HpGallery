package com.hpgallery

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

@Composable
fun AppNavigation(
    appViewModel: AppViewModel
) {
    val navController = rememberNavController()
    val isDarkTheme by appViewModel.isDarkTheme.collectAsState()

    NavHost(navController = navController, startDestination = NavigationRoutes.CHARACTER_LIST) {
        composable(NavigationRoutes.CHARACTER_LIST) {
            HpCharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("${NavigationRoutes.CHARACTER_DETAIL}/$characterId")
                },
                isDarkTheme = isDarkTheme,
                onToggleTheme = { appViewModel.toggleTheme() }
            )
        }
        composable(
            route = "${NavigationRoutes.CHARACTER_DETAIL}/{${NavigationRoutes.CHARACTER_ID_ARG}}",
            arguments = listOf(navArgument(NavigationRoutes.CHARACTER_ID_ARG) { type = NavType.StringType })
        ) {
            HpCharacterDetailsScreen(
                onBackClick = { navController.popBackStack() },
                isDarkTheme = isDarkTheme,
                onToggleTheme = { appViewModel.toggleTheme() }
            )
        }
    }
}