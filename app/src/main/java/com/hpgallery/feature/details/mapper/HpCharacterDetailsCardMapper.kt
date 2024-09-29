package com.hpgallery.feature.details.mapper

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData

fun HpCharacter.toHpCharacterDetailsCardViewData(): HpCharacterDetailsCardViewData {
    return HpCharacterDetailsCardViewData(
        id = this.id,
        name = this.name,
        actor = this.actor,
        species = this.species,
        house = this.house,
        yearOfBirth = this.yearOfBirth,
        image = this.image
    )
}