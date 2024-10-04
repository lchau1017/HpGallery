package com.hpgallery.domain.repository

import kotlinx.coroutines.flow.Flow

interface HpPreferencesRepository {
    fun getThemePreference(): Flow<Boolean>
    suspend fun saveThemePreference(isDarkTheme: Boolean)
}
