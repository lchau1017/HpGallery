package com.hpgallery.ui.utils

import androidx.compose.ui.graphics.Color
import com.hpgallery.ui.theme.DefaultHouseColor
import com.hpgallery.ui.theme.GryffindorColor
import com.hpgallery.ui.theme.HufflepuffColor
import com.hpgallery.ui.theme.RavenclawColor
import com.hpgallery.ui.theme.SlytherinColor

object HouseColorUtils {

    fun getHouseColor(house: String?): Color {
        return when (house) {
            "Gryffindor" -> GryffindorColor
            "Slytherin" -> SlytherinColor
            "Ravenclaw" -> RavenclawColor
            "Hufflepuff" -> HufflepuffColor
            else -> DefaultHouseColor
        }
    }
}