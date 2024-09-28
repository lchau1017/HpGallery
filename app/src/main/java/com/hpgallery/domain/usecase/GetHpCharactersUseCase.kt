package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHpCharactersUseCase @Inject constructor(private val repository: HpCharacterRepository) {
    suspend operator fun invoke(): Flow<List<HpCharacter>> {
        return flow {
            emit(repository.getHpCharacters())
        }
    }
}