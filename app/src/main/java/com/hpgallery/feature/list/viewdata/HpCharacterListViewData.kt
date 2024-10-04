package com.hpgallery.feature.list.viewdata

sealed interface HpCharacterListViewData {

    data object Loading : HpCharacterListViewData

    data object Empty : HpCharacterListViewData

    data class Error(
        val hpCharacterListErrorViewData: HpCharacterListErrorViewData
    ) : HpCharacterListViewData

    data class Success(
        val hpCharacterRowViewData: List<HpCharacterRowViewData>
    ) : HpCharacterListViewData
}
