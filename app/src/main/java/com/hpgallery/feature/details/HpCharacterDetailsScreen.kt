package com.hpgallery.feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsViewData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HpCharacterDetailsScreen(
    viewModel: HpCharacterDetailsViewModel = hiltViewModel(), onBackClick: () -> Unit
) {
    val characterListState = viewModel.hpCharacterDetailsState.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Details") }, navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            when (val viewState = characterListState.value) {
                is HpCharacterDetailsViewData.Empty -> {
                }

                is HpCharacterDetailsViewData.Error -> {}
                is HpCharacterDetailsViewData.Success -> {
                    CharacterDetails(viewState.hpCharacterDetailsCardViewData)
                }
            }
        }
    }
}

@Composable
fun CharacterDetails(characterDetails: HpCharacterDetailsCardViewData) {
    HpCharacterDetailsCard(viewData = characterDetails)
}