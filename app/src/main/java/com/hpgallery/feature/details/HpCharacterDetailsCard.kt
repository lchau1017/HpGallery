package com.hpgallery.feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.ui.component.HpHouseColorIndicator
import com.hpgallery.ui.component.HpImage
import com.hpgallery.ui.component.HpText
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.HpShapes
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.theme.LocalTypography
import com.hpgallery.ui.utils.DualModePreview
import com.hpgallery.ui.utils.HouseColorUtils

@Composable
fun HpCharacterDetailsCard(
    viewData: HpCharacterDetailsCardViewData, modifier: Modifier = Modifier
) {
    Card(
        shape = HpShapes.medium,
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(LocalColourScheme.current.backgroundSecondary)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            val houseColor = HouseColorUtils.getHouseColor(viewData.house)
            HpHouseColorIndicator(
                color = houseColor, modifier = Modifier.padding(end = 8.dp)
            )
            Column(
                modifier = Modifier
                    .background(LocalColourScheme.current.backgroundSecondary)
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!viewData.image.isNullOrEmpty()) {
                    HpImage(
                        imageUrl = viewData.image,
                        contentDescription = viewData.name,
                        modifier = Modifier.size(120.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                HpText(
                    text = viewData.name,
                    style = LocalTypography.current.headingPrimaryLarge,
                    color = LocalTypography.current.headingPrimaryLarge.color,
                )
                Spacer(modifier = Modifier.height(16.dp))
                HpText(
                    text = "Actor: ${viewData.actor}",
                    style = LocalTypography.current.headingSecondaryLarge,
                    color = LocalTypography.current.headingSecondaryLarge.color,
                )
                Spacer(modifier = Modifier.height(16.dp))
                HpText(
                    text = "Species: ${viewData.species}",
                    style = LocalTypography.current.labelSmall,
                    color = LocalTypography.current.labelSmall.color,
                )
                Spacer(modifier = Modifier.height(16.dp))
                HpText(
                    text = "DOB: ${viewData.dateOfBirth}",
                    style = LocalTypography.current.labelSmall,
                    color = LocalTypography.current.labelSmall.color,
                )
                Spacer(modifier = Modifier.height(16.dp))
                HpText(
                    text = "Status: ${viewData.status}",
                    style = LocalTypography.current.labelSmall,
                    color = LocalTypography.current.labelSmall.color,
                )
            }

        }
    }

}

@DualModePreview
@Composable
fun HpCharacterRowPreview() {
    HpGalleryTheme {
        HpCharacterDetailsCard(
            HpCharacterDetailsCardViewData(
                id = "1",
                name = "Harry Potter",
                actor = "Daniel Radcliffe",
                species = "Wizard",
                house = "Gryffindor",
                dateOfBirth = "31 July 1980",
                image = "https://hp-api.herokuapp.com/images/harry.jpg",
                status = "Alive"
            )
        )
    }
}