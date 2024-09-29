package com.hpgallery.feature.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData
import com.hpgallery.ui.component.HpCircularProgressIndicator
import com.hpgallery.ui.component.HpFloatingActionButton
import com.hpgallery.ui.component.HpSearchBar
import com.hpgallery.ui.theme.LocalColourScheme

@Composable
fun HpCharacterListScreen(
    viewData: HpCharacterListViewData,
    searchQuery: String,
    isDarkTheme: Boolean,
    updateSearchQuery: (String) -> Unit,
    onCharacterClick: (String) -> Unit,
    onToggleTheme: () -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf(searchQuery) }
    // Sync the local search query state with the ViewModel's state
    LaunchedEffect(searchQuery) {
        updateSearchQuery(searchQuery)
    }
    Scaffold(topBar = {
        HpSearchBar(query = searchQuery, onQueryChange = { query ->
            searchQuery = query // Update the local mutable state
        })
    }, floatingActionButton = {
        HpFloatingActionButton(isDarkTheme, onToggleTheme)
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalColourScheme.current.backgroundPrimary)
                .padding(paddingValues)
        ) {
            when (viewData) {
                is HpCharacterListViewData.Loading -> {
                    HpCircularProgressIndicator(color = LocalColourScheme.current.indicator)
                }

                is HpCharacterListViewData.Error -> {}
                is HpCharacterListViewData.Success -> {
                    CharacterList(viewData.hpCharacterRowViewData, onCharacterClick)
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