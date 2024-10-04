package com.hpgallery.feature.details.mapper

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.feature.details.viewdata.HpCharacterDetailsCardViewData
import com.hpgallery.ui.HpDateUtils

fun HpCharacter.toHpCharacterDetailsCardViewData(): HpCharacterDetailsCardViewData {
    return HpCharacterDetailsCardViewData(
        id = this.id,
        name = this.name,
        actor = this.actor,
        species = this.species,
        house = this.house,
        dateOfBirth = HpDateUtils.formatDate(this.dateOfBirth),
        image = this.image,
        status = if (this.alive) "Alive" else "Deceased"
    )
}
