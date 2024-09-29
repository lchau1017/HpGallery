package com.hpgallery.feature.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsViewData

@Composable
fun HpCharacterDetailsScreen(
    viewModel: HpCharacterDetailsViewModel = hiltViewModel()
) {

    val characterListState = viewModel.hpCharacterDetailsState.collectAsState()
    when (val viewState = characterListState.value) {
        is HpCharacterDetailsViewData.Empty -> {
        }

        is HpCharacterDetailsViewData.Error -> {}
        is HpCharacterDetailsViewData.Success -> {
            CharacterDetails(viewState.hpCharacterDetailsCardViewData)
        }
    }
}

@Composable
fun CharacterDetails(characterDetails: HpCharacterDetailsCardViewData) {
    HpCharacterDetailsCard(viewData = characterDetails)
}