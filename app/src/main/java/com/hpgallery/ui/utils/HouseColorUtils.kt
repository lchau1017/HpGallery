package com.hpgallery.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hpgallery.ui.theme.LocalColourScheme

object HouseColorUtils {

    @Composable
    fun getHouseColor(house: String?): Color {
        return when (house) {
            "Gryffindor" -> LocalColourScheme.current.gryffindor
            "Slytherin" -> LocalColourScheme.current.slytherin
            "Ravenclaw" -> LocalColourScheme.current.ravenclaw
            "Hufflepuff" -> LocalColourScheme.current.hufflepuff
            else -> Color.Transparent
        }
    }
}