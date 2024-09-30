package com.hpgallery.data.mapper

import com.hpgallery.data.local.HpCharacterEntity
import com.hpgallery.data.remote.HpCharacterResponse
import com.hpgallery.domain.model.HpCharacter

fun HpCharacterResponse.toDomainModel(): HpCharacter {
    return HpCharacter(
        id = this.id,
        name = this.name,
        actor = this.actor,
        species = this.species,
        house = this.house,
        dateOfBirth = this.dateOfBirth,
        alive = this.alive,
        image = this.image
    )
}

// Map network response to database entity
fun HpCharacterResponse.toEntity(): HpCharacterEntity {
    return HpCharacterEntity(
        id = this.id,
        name = this.name,
        actor = this.actor,
        species = this.species,
        house = this.house,
        dateOfBirth = this.dateOfBirth,
        alive = this.alive,
        image = this.image
    )
}

// Map database entity to domain model
fun HpCharacterEntity.toDomainModel(): HpCharacter {
    return HpCharacter(
        id = this.id,
        name = this.name,
        actor = this.actor,
        species = this.species,
        house = this.house,
        dateOfBirth = this.dateOfBirth,
        alive = this.alive,
        image = this.image
    )
}
