package com.hpgallery.feature.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpgallery.feature.list.viewdata.HpCharacterListViewData
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData

@Composable
fun HpCharacterListScreen(viewModel: HpCharacterListViewModel = hiltViewModel()) {
    val characterListState = viewModel.hpCharacterListState.collectAsState()
    when (val viewState = characterListState.value) {
        is HpCharacterListViewData.Loading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }

        is HpCharacterListViewData.Error -> {}
        is HpCharacterListViewData.Success -> {
            CharacterList(viewState.hpCharacterRowViewData)
        }
    }
}

@Composable
fun CharacterList(characterList: List<HpCharacterRowViewData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characterList) { viewData ->
            HpCharacterRow(viewData = viewData)
        }
    }
}