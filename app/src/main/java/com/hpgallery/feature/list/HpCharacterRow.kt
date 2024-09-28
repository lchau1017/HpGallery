package com.hpgallery.feature.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData
import com.hpgallery.ui.component.HpHouseColorIndicator
import com.hpgallery.ui.component.HpText
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.HpShapes
import com.hpgallery.ui.theme.LocalColourScheme
import com.hpgallery.ui.theme.LocalTypography
import com.hpgallery.ui.utils.DualModePreview
import com.hpgallery.ui.utils.HouseColorUtils

@Composable
fun HpCharacterRow(
    viewData: HpCharacterRowViewData,
    modifier: Modifier = Modifier
) {
    Card(
        shape = HpShapes.medium, modifier = modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(LocalColourScheme.current.backgroundSecondary)
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.Start
        ) {
            // House Color Indicator using Material 3
            val houseColor = HouseColorUtils.getHouseColor(viewData.house)
            HpHouseColorIndicator(
                color = houseColor, modifier = Modifier.padding(end = 8.dp)
            )

            // Text Information
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                HpText(
                    text = viewData.name,
                    style = LocalTypography.current.headingPrimaryLarge,
                    color = LocalTypography.current.headingPrimaryLarge.color,
                )
                HpText(
                    text = "Actor: ${viewData.actor}",
                    style = LocalTypography.current.headingSecondaryLarge,
                    color = LocalTypography.current.headingSecondaryLarge.color,
                )
                HpText(
                    text = "Species: ${viewData.species}",
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
        HpCharacterRow(
            HpCharacterRowViewData(
                id = "1",
                name = "Harry Potter",
                actor = "Daniel Radcliffe",
                species = "Wizard",
                house = "Gryffindor",
            )
        )
    }
}