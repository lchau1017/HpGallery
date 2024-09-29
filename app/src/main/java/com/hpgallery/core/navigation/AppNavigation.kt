package com.hpgallery.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hpgallery.core.app.AppViewModel
import com.hpgallery.core.event.UiEvent
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

    // Initialize ViewModels here in the top-level composable
    val listViewModel: HpCharacterListViewModel = hiltViewModel()
    val detailsViewModel: HpCharacterDetailsViewModel = hiltViewModel()

    // Extract viewData and states from the ViewModels
    val hpCharacterListState by listViewModel.hpCharacterListState.collectAsState()
    val searchQuery by listViewModel.searchQuery.collectAsState()
    val hpCharacterDetailsState by detailsViewModel.hpCharacterDetailsState.collectAsState()

    // Collect and handle UI events using LaunchedEffect
    LaunchedEffect(Unit) {
        appViewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.BackPress -> {
                    navController.popBackStack()
                }

                is UiEvent.NavigateToCharacterDetail -> {
                    navController.navigate("${NavigationRoutes.CHARACTER_DETAIL}/${event.characterId}")
                }
            }
        }
    }

    NavHost(navController = navController, startDestination = NavigationRoutes.CHARACTER_LIST) {
        composable(NavigationRoutes.CHARACTER_LIST) {
            HpCharacterListScreen(isDarkTheme = isDarkTheme,
                viewData = hpCharacterListState,
                searchQuery = searchQuery,
                updateSearchQuery = { query -> listViewModel.updateSearchQuery(query) },
                onCharacterClick = { characterId -> appViewModel.onCharacterClicked(characterId) },
                onToggleTheme = { appViewModel.toggleTheme() })
        }
        composable(
            route = "${NavigationRoutes.CHARACTER_DETAIL}/{${NavigationRoutes.CHARACTER_ID_ARG}}",
            arguments = listOf(navArgument(NavigationRoutes.CHARACTER_ID_ARG) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString(NavigationRoutes.CHARACTER_ID_ARG)
            // Fetch details based on characterId if necessary
            if (characterId != null) {
                detailsViewModel.loadCharacterDetails(characterId)
            }

            HpCharacterDetailsScreen(viewData = hpCharacterDetailsState,
                isDarkTheme = isDarkTheme,
                onBackClick = { appViewModel.onBackPressed() },
                onToggleTheme = { appViewModel.toggleTheme() })
        }
    }
}