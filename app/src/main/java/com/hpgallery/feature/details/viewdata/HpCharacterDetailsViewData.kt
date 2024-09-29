package com.hpgallery.feature.details.viewdata

sealed interface HpCharacterDetailsViewData {

    data object Empty : HpCharacterDetailsViewData

    data class Error(
        val hpCharacterDetailsErrorViewData: HpCharacterDetailsErrorViewData,
    ) : HpCharacterDetailsViewData

    data class Success(
        val hpCharacterDetailsCardViewData: HpCharacterDetailsCardViewData,
    ) : HpCharacterDetailsViewData
}
