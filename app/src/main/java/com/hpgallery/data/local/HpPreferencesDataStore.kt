package com.hpgallery.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.hpgallery.domain.repository.HpPreferencesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Singleton
class HpPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) : HpPreferencesRepository {

    private val THEME_KEY = booleanPreferencesKey("is_dark_theme")

    override fun getThemePreference(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    override suspend fun saveThemePreference(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkTheme
        }
    }
}
