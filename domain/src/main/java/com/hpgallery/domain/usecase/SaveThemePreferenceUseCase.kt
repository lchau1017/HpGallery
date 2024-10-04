package com.hpgallery.domain.usecase

import com.hpgallery.domain.repository.HpPreferencesRepository
import javax.inject.Inject

class SaveThemePreferenceUseCase @Inject constructor(
    private val preferencesRepository: HpPreferencesRepository
) {
    suspend operator fun invoke(isDarkTheme: Boolean) {
        preferencesRepository.saveThemePreference(isDarkTheme)
    }
}
