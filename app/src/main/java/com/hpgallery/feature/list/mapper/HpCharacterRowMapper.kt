package com.hpgallery.feature.list.mapper

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.feature.list.viewdata.HpCharacterRowViewData

fun HpCharacter.toHpCharacterRowViewData(): HpCharacterRowViewData {
    return HpCharacterRowViewData(
        id = this.id,
        name = this.name,
        actor = this.actor,
        species = this.species,
        house = this.house
    )
}