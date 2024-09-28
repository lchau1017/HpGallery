package com.hpgallery.feature.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.hpgallery.ui.component.HpHouseColorIndicator
import com.hpgallery.ui.component.HpText
import com.hpgallery.ui.theme.HpGalleryTheme
import com.hpgallery.ui.theme.HpShapes
import com.hpgallery.ui.theme.HpTypography
import com.hpgallery.ui.utils.DualModePreview
import com.hpgallery.ui.utils.HouseColorUtils

@Composable
fun HpCharacterRow(
    name: String, actor: String, species: String, house: String?, modifier: Modifier = Modifier
) {
    Card(
        shape = HpShapes.medium, modifier = modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            // House Color Indicator using Material 3
            val houseColor = HouseColorUtils.getHouseColor(house)
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
                    text = name,
                    style = HpTypography.headlineMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                HpText(
                    text = "Actor: $actor",
                    style = HpTypography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                HpText(
                    text = "Species: $species",
                    style = HpTypography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
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
            name = "Harry Potter",
            actor = "Daniel Radcliffe",
            species = "Wizard",
            house = "Gryffindor"
        )
    }
}