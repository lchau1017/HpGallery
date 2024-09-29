package com.hpgallery.feature.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData
import com.hpgallery.ui.component.HpCircularProgressIndicator
import com.hpgallery.ui.theme.LocalColourScheme

@Composable
fun HpCharacterListScreen(
    viewModel: HpCharacterListViewModel = hiltViewModel(), onCharacterClick: (String) -> Unit
) {
    val characterListState = viewModel.hpCharacterListState.collectAsState()
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