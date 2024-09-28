package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository

class GetHpCharactersUseCase(private val repository: HpCharacterRepository) {
    suspend operator fun invoke(): List<HpCharacter> {
        return repository.getHpCharacters()
    }
}