package com.hpgallery.domain.usecase

import com.hpgallery.domain.repository.HpPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetThemePreferenceUseCase @Inject constructor(
    private val preferencesRepository: HpPreferencesRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return preferencesRepository.getThemePreference()
    }
}