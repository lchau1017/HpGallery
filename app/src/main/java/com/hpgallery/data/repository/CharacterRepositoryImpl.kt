package com.hpgallery.data.repository

import com.hpgallery.data.local.HpCharacterDao
import com.hpgallery.data.mapper.toDomainModel
import com.hpgallery.data.mapper.toEntity
import com.hpgallery.data.remote.HpApiService
import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import javax.inject.Inject

class HpCharacterRepositoryImpl @Inject constructor(
    private val hpApiService: HpApiService, private val hpCharacterDao: HpCharacterDao
) : HpCharacterRepository {

    override suspend fun getHpCharacters(): List<HpCharacter> {
        return try {
            // Attempt to fetch data from the network
            val charactersFromNetwork = hpApiService.getCharacters()
            // Cache data in the local database
            hpCharacterDao.insertAll(charactersFromNetwork.map { it.toEntity() })
            // Return the freshly fetched data
            charactersFromNetwork.map { it.toDomainModel() }
        } catch (e: Exception) {
            // If network fails, return data from the local database
            hpCharacterDao.getAllCharacters().map { it.toDomainModel() }
        }
    }

    override suspend fun getCharacterDetails(characterId: String): HpCharacter? {
        // Retrieve from the local database since it's already cached
        return hpCharacterDao.getAllCharacters().find { it.id == characterId }?.toDomainModel()
    }
}