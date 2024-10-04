package com.hpgallery.feature.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hpgallery.feature.list.viewdata.HpCharacterListErrorViewData
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData
import com.hpgallery.ui.DualModePreview
import com.hpgallery.ui.component.HpCircularProgressIndicator
import com.hpgallery.ui.component.HpEmptyScreen
import com.hpgallery.ui.component.HpErrorScreen
import com.hpgallery.ui.component.HpFloatingActionButton
import com.hpgallery.ui.component.HpSearchBar
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HpCharacterListScreen(
    viewData: HpCharacterListViewData,
    searchQueryString: String,
    isDarkTheme: Boolean,
    updateSearchQuery: (String) -> Unit,
    onCharacterClick: (String) -> Unit,
    onToggleTheme: () -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf(searchQueryString) }
    // Sync the local search query state with the ViewModel's state
    LaunchedEffect(searchQuery) {
        updateSearchQuery(searchQuery)
    }
    Scaffold(topBar = {
        HpSearchBar(query = searchQuery, onQueryChange = { query ->
            searchQuery = query
        })
    }, floatingActionButton = {
            HpFloatingActionButton(isDarkTheme, onToggleTheme)
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalColourScheme.current.backgroundSecondary)
                .padding(paddingValues)
        ) {
            when (viewData) {
                is HpCharacterListViewData.Loading -> {
                    HpCircularProgressIndicator(color = LocalColourScheme.current.indicator)
                }

                is HpCharacterListViewData.Error -> {
                    HpErrorScreen(viewData.hpCharacterListErrorViewData.errorMessage)
                }

                is HpCharacterListViewData.Success -> {
                    CharacterList(viewData.hpCharacterRowViewData, onCharacterClick)
                }

                HpCharacterListViewData.Empty -> {
                    HpEmptyScreen()
                }
            }
        }
    }
}

@Composable
fun CharacterList(characterList: List<HpCharacterRowViewData>, onCharacterClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characterList) { viewData ->
            HpCharacterRow(viewData = viewData) {
                onCharacterClick(viewData.id)
            }
        }
    }
}

@DualModePreview
@Composable
fun HpCharacterListScreenSuccessPreview() {
    HpGalleryTheme {
        HpCharacterListScreen(
            isDarkTheme = false,
            viewData = HpCharacterListViewData.Success(
                listOf(
                    HpCharacterRowViewData(
                        "1",
                        "Harry Potter",
                        "Daniel Radcliffe",
                        "Wizard",
                        "Gryffindor"
                    )
                )
            ),
            searchQueryString = "Harry Potter",
            updateSearchQuery = { /* Do nothing */ },
            onCharacterClick = { /* Do nothing */ },
            onToggleTheme = { /* Do nothing */ }
        )
    }
}

@DualModePreview
@Composable
fun HpCharacterListScreenLoadingPreview() {
    HpGalleryTheme {
        HpCharacterListScreen(
            isDarkTheme = false,
            viewData = HpCharacterListViewData.Loading,
            searchQueryString = "Harry Potter",
            updateSearchQuery = { /* Do nothing */ },
            onCharacterClick = { /* Do nothing */ },
            onToggleTheme = { /* Do nothing */ }
        )
    }
}

@DualModePreview
@Composable
fun HpCharacterListScreenEmptyPreview() {
    HpGalleryTheme {
        HpCharacterListScreen(
            isDarkTheme = false,
            viewData = HpCharacterListViewData.Empty,
            searchQueryString = "Harry Potter",
            updateSearchQuery = { /* Do nothing */ },
            onCharacterClick = { /* Do nothing */ },
            onToggleTheme = { /* Do nothing */ }
        )
    }
}

@DualModePreview
@Composable
fun HpCharacterListScreenErrorPreview() {
    HpGalleryTheme {
        HpCharacterListScreen(
            isDarkTheme = false,
            viewData = HpCharacterListViewData.Error(
                HpCharacterListErrorViewData("An error occurred")
            ),
            searchQueryString = "Harry Potter",
            updateSearchQuery = { /* Do nothing */ },
            onCharacterClick = { /* Do nothing */ },
            onToggleTheme = { /* Do nothing */ }
        )
    }
}
