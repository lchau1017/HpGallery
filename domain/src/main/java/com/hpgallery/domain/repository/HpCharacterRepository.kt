package com.hpgallery.domain.repository

import com.hpgallery.domain.model.HpCharacter

interface HpCharacterRepository {
    suspend fun getHpCharacters(): List<HpCharacter>
    suspend fun getCharacterDetails(characterId: String): HpCharacter?
}
