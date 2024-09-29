package com.hpgallery.feature.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData
import com.hpgallery.ui.component.HpCircularProgressIndicator
import com.hpgallery.ui.component.HpSearchBar
import com.hpgallery.ui.theme.LocalColourScheme

@Composable
fun HpCharacterListScreen(
    viewModel: HpCharacterListViewModel = hiltViewModel(), onCharacterClick: (String) -> Unit
) {
    var searchQuery by rememberSaveable { mutableStateOf(viewModel.searchQuery.value) }
    val characterListState = viewModel.hpCharacterListState.collectAsState()
    // Sync the local search query state with the ViewModel's state
    LaunchedEffect(searchQuery) {
        viewModel.updateSearchQuery(searchQuery)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        HpSearchBar(query = searchQuery, onQueryChanged = { query ->
            searchQuery = query // Update the local mutable state
        })
        when (val viewState = characterListState.value) {
            is HpCharacterListViewData.Loading -> {
                HpCircularProgressIndicator(LocalColourScheme.current.accentPrimary)
            }

            is HpCharacterListViewData.Error -> {}
            is HpCharacterListViewData.Success -> {
                CharacterList(viewState.hpCharacterRowViewData, onCharacterClick)
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