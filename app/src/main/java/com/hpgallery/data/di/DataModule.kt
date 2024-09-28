package com.hpgallery.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hpgallery.data.local.AppDatabase
import com.hpgallery.data.local.HpCharacterDao
import com.hpgallery.data.remote.HpApiService
import com.hpgallery.data.repository.HpCharacterRepositoryImpl
import com.hpgallery.domain.repository.HpCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl("https://hp-api.onrender.com/api/").client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @Singleton
    fun provideHpApiService(retrofit: Retrofit): HpApiService {
        return retrofit.create(HpApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java, "hp_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: AppDatabase): HpCharacterDao {
        return appDatabase.hpCharacterDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHpCharacterRepository(
        characterRepositoryImpl: HpCharacterRepositoryImpl
    ): HpCharacterRepository
}