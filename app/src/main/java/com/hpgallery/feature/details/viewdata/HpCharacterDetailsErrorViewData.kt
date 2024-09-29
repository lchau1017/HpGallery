package com.hpgallery.feature.details.viewdata

data class HpCharacterDetailsErrorViewData(
    val errorMessage: String,
) {
    companion object {
        val DEFAULT = HpCharacterDetailsErrorViewData(
            errorMessage = "Could’t load character",
        )
    }
}