package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchHpCharactersUseCase @Inject constructor(private val repository: HpCharacterRepository) {
    suspend operator fun invoke(query: String): Flow<List<HpCharacter>> {
        return flow {
            // Fetch the character list from the repository
            val characters = repository.getHpCharacters()

            // Perform the filtering based on the query (both name and actor)
            val filteredCharacters = if (query.isBlank()) {
                characters
            } else {
                characters.filter {
                    it.name.contains(query, ignoreCase = true) ||
                        it.actor.contains(query, ignoreCase = true)
                }
            }

            emit(filteredCharacters)
        }
    }
}
