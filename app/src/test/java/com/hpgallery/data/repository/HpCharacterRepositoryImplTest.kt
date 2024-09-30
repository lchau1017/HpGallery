package com.hpgallery.data.repository

import com.hpgallery.data.local.HpCharacterDao
import com.hpgallery.data.local.HpCharacterEntity
import com.hpgallery.data.mapper.toDomainModel
import com.hpgallery.data.mapper.toEntity
import com.hpgallery.data.remote.HpApiService
import com.hpgallery.data.remote.HpCharacterResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HpCharacterRepositoryImplTest {

    private lateinit var repository: HpCharacterRepositoryImpl
    private val apiService: HpApiService = mockk()
    private val characterDao: HpCharacterDao = mockk()

    @Before
    fun setUp() {
        repository = HpCharacterRepositoryImpl(apiService, characterDao)
    }

    @Test
    fun `given network is successful when getHpCharacters is called then save to database and return data`() =
        runTest {
            // Given
            val characterResponseList = listOf(
                HpCharacterResponse(
                    "1",
                    "Harry Potter",
                    "Daniel Radcliffe",
                    "Human",
                    "Gryffindor",
                    "31-07-1980",
                    true,
                    "https://example.com/harry.jpg"
                )
            )
            val characterEntities = characterResponseList.map { it.toEntity() }
            val expectedCharacters = characterEntities.map { it.toDomainModel() }

            coEvery { apiService.getCharacters() } returns characterResponseList
            coEvery { characterDao.insertAll(characterEntities) } returns Unit
            coEvery { characterDao.getAllCharacters() } returns characterEntities

            // When
            val result = repository.getHpCharacters()

            // Then
            assertEquals(expectedCharacters, result)
            coVerify(exactly = 1) { apiService.getCharacters() }
            coVerify(exactly = 1) { characterDao.insertAll(characterEntities) }
        }

    @Test
    fun `given network failure when getHpCharacters is called then return data from database`() =
        runTest {
            // Given
            val characterEntities = listOf(
                HpCharacterEntity(
                    "1",
                    "Harry Potter",
                    "Daniel Radcliffe",
                    "Human",
                    "Gryffindor",
                    "31-07-1980",
                    true,
                    "https://example.com/harry.jpg"
                )
            )
            val expectedCharacters = characterEntities.map { it.toDomainModel() }

            coEvery { apiService.getCharacters() } throws Exception("Network error")
            coEvery { characterDao.getAllCharacters() } returns characterEntities

            // When
            val result = repository.getHpCharacters()

            // Then
            assertEquals(expectedCharacters, result)
            coVerify(exactly = 1) { apiService.getCharacters() }
            coVerify(exactly = 1) { characterDao.getAllCharacters() }
        }
}
