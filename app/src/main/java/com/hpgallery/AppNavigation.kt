package com.hpgallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hpgallery.feature.details.HpCharacterDetailsScreen
import com.hpgallery.feature.details.HpCharacterDetailsViewModel
import com.hpgallery.feature.list.HpCharacterListScreen
import com.hpgallery.feature.list.HpCharacterListViewModel

@Composable
fun AppNavigation(
    appViewModel: AppViewModel,
) {
    val navController = rememberNavController()
    val isDarkTheme by appViewModel.isDarkTheme.collectAsState()

    NavHost(navController = navController, startDestination = NavigationRoutes.CHARACTER_LIST) {
        composable(NavigationRoutes.CHARACTER_LIST) {
            val listViewModel: HpCharacterListViewModel = hiltViewModel()
            val hpCharacterListState by listViewModel.hpCharacterListState.collectAsState()
            val searchQuery by listViewModel.searchQuery.collectAsState()

            HpCharacterListScreen(isDarkTheme = isDarkTheme,
                viewData = hpCharacterListState,
                searchQuery = searchQuery,
                updateSearchQuery = { query -> listViewModel.updateSearchQuery(query) },
                onCharacterClick = { characterId ->
                    navController.navigate("${NavigationRoutes.CHARACTER_DETAIL}/$characterId")
                },
                onToggleTheme = { appViewModel.toggleTheme() })
        }
        composable(
            route = "${NavigationRoutes.CHARACTER_DETAIL}/{${NavigationRoutes.CHARACTER_ID_ARG}}",
            arguments = listOf(navArgument(NavigationRoutes.CHARACTER_ID_ARG) {
                type = NavType.StringType
            })
        ) {
            val detailsViewModel: HpCharacterDetailsViewModel = hiltViewModel()

            val hpCharacterDetailsState by detailsViewModel.hpCharacterDetailsState.collectAsState()

            HpCharacterDetailsScreen(viewData = hpCharacterDetailsState,
                isDarkTheme = isDarkTheme,
                onBackClick = { navController.popBackStack() },
                onToggleTheme = { appViewModel.toggleTheme() })
        }
    }
}