package com.hpgallery.core.app

import android.content.Context
import com.hpgallery.data.local.HpPreferencesDataStore
import com.hpgallery.domain.repository.HpPreferencesRepository
import com.hpgallery.domain.usecase.GetThemePreferenceUseCase
import com.hpgallery.domain.usecase.SaveThemePreferenceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePreferencesRepository(
        @ApplicationContext context: Context
    ): HpPreferencesRepository {
        return HpPreferencesDataStore(context)
    }

    @Provides
    fun provideGetThemePreferenceUseCase(
        preferencesRepository: HpPreferencesRepository
    ): GetThemePreferenceUseCase {
        return GetThemePreferenceUseCase(preferencesRepository)
    }

    @Provides
    fun provideSaveThemePreferenceUseCase(
        preferencesRepository: HpPreferencesRepository
    ): SaveThemePreferenceUseCase {
        return SaveThemePreferenceUseCase(preferencesRepository)
    }
}
