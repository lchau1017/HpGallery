package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository

class SearchHpCharactersUseCase(private val repository: HpCharacterRepository) {
    suspend operator fun invoke(query: String): List<HpCharacter> {
        val characters = repository.getHpCharacters()
        return characters.filter {
            it.name.contains(query, ignoreCase = true) || it.actor.contains(
                query, ignoreCase = true
            )
        }
    }
}