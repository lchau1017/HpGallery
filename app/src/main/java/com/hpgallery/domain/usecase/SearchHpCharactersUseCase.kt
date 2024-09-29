package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchHpCharactersUseCase @Inject constructor(private val repository: HpCharacterRepository) {
    suspend operator fun invoke(query: String): Flow<List<HpCharacter>> {
        val characters = repository.getHpCharacters()
        return flow {
            if(query.isEmpty()) {
                emit(characters)
                return@flow
            }
            emit(characters.filter {
                it.name.contains(query, ignoreCase = true) || it.actor.contains(
                    query, ignoreCase = true
                )
            })
        }
    }
}