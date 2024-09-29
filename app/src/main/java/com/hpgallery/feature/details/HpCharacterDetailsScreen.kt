package com.hpgallery.feature.details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.hpgallery.R
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsErrorViewData
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsViewData
import com.hpgallery.ui.component.HpEmptyScreen
import com.hpgallery.ui.component.HpFloatingActionButton
import com.hpgallery.ui.component.HpText
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.theme.LocalTypography
import com.hpgallery.ui.utils.DualModePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HpCharacterDetailsScreen(
    viewData: HpCharacterDetailsViewData,
    onBackClick: () -> Unit,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LocalColourScheme.current.backgroundPrimary
        ), title = {
            HpText(
                text = stringResource(id = R.string.details_title),
                color = LocalColourScheme.current.textPrimary,
                style = LocalTypography.current.headingSecondaryMedium
            )
        }, navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = LocalColourScheme.current.textPrimary
                )
            }
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
                is HpCharacterDetailsViewData.Empty -> {
                    HpEmptyScreen()
                }

                is HpCharacterDetailsViewData.Error -> {
                    Toast.makeText(
                        LocalContext.current,
                        "An error occurred: ${viewData.hpCharacterDetailsErrorViewData.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is HpCharacterDetailsViewData.Success -> {
                    CharacterDetails(viewData.hpCharacterDetailsCardViewData)
                }
            }
        }
    }
}

@Composable
fun CharacterDetails(characterDetails: HpCharacterDetailsCardViewData) {
    HpCharacterDetailsCard(viewData = characterDetails)
}

@DualModePreview
@Composable
fun HpCharacterDetailsScreenSuccessPreview() {
    HpGalleryTheme {
        HpCharacterDetailsScreen(isDarkTheme = false, viewData = HpCharacterDetailsViewData.Success(
            HpCharacterDetailsCardViewData(
                id = "1",
                name = "Harry Potter",
                actor = "Daniel Radcliffe",
                species = "Human",
                dateOfBirth = "31 July 1980",
                status = "Alive",
                house = "Gryffindor",
                image = "https://hp-api.herokuapp.com/images/harry.jpg"
            )
        ), onBackClick = { /* Do nothing */ }, onToggleTheme = { /* Do nothing */ })
    }
}

@DualModePreview
@Composable
fun HpCharacterDetailsScreenErrorPreview() {
    HpGalleryTheme {
        HpCharacterDetailsScreen(isDarkTheme = false, viewData = HpCharacterDetailsViewData.Error(
            HpCharacterDetailsErrorViewData("An error occurred")
        ), onBackClick = { /* Do nothing */ }, onToggleTheme = { /* Do nothing */ })
    }
}

@DualModePreview
@Composable
fun HpCharacterDetailsScreenEmptyPreview() {
    HpGalleryTheme {
        HpCharacterDetailsScreen(isDarkTheme = false,
            viewData = HpCharacterDetailsViewData.Empty,
            onBackClick = { /* Do nothing */ },
            onToggleTheme = { /* Do nothing */ })
    }
}