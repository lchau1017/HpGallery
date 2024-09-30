package com.hpgallery.domain.usecase

import com.hpgallery.domain.repository.HpPreferencesRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetThemePreferenceUseCase @Inject constructor(
    private val preferencesRepository: HpPreferencesRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return preferencesRepository.getThemePreference()
    }
}
