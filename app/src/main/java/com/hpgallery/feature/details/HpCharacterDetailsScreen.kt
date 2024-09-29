package com.hpgallery.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hpgallery.R
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsViewData
import com.hpgallery.ui.theme.LocalColourScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HpCharacterDetailsScreen(
    viewModel: HpCharacterDetailsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    val characterListState = viewModel.hpCharacterDetailsState.collectAsState()
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalColourScheme.current.backgroundPrimary
        ),
            title = { Text(text = "Details", color = LocalColourScheme.current.textPrimary) },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = LocalColourScheme.current.textPrimary
                    )
                }
            })
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = onToggleTheme, containerColor = LocalColourScheme.current.fab
        ) {
            Icon(
                painter = painterResource(
                    id = if (isDarkTheme) R.drawable.ic_light_mode else R.drawable.ic_dark_mode
                ),
                contentDescription = "Toggle Theme",
                tint = LocalColourScheme.current.backgroundPrimary
            )
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LocalColourScheme.current.backgroundPrimary)
                .padding(paddingValues)
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