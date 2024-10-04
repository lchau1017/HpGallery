package com.hpgallery.feature.list.viewdata

data class HpCharacterListErrorViewData(
    val errorMessage: String
) {
    companion object {
        val DEFAULT = HpCharacterListErrorViewData(
            errorMessage = "Could’t load characters"
        )
    }
}
