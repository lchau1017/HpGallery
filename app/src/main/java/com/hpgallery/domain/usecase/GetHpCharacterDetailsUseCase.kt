package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository

class GetHpCharacterDetailsUseCase(private val repository: HpCharacterRepository) {
    suspend operator fun invoke(characterId: String): HpCharacter? {
        return repository.getCharacterDetails(characterId)
    }
}