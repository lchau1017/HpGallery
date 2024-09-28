package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetHpCharacterDetailsUseCaseTest {

    private lateinit var getCharacterDetailsUseCase: GetHpCharacterDetailsUseCase
    private lateinit var repository: HpCharacterRepository

    @Before
    fun setUp() {
        repository = mockk()
        getCharacterDetailsUseCase = GetHpCharacterDetailsUseCase(repository)
    }

    @Test
    fun `given valid character ID when getting character details then return character`() = runBlocking {
        // Given
        val characterId = "123"
        val expectedCharacter = HpCharacter(
            id = characterId,
            name = "Harry Potter",
            actor = "Daniel Radcliffe",
            species = "Human",
            house = "Gryffindor",
            dateOfBirth = "31-07-1980",
            isAlive = true,
            imageUrl = "https://example.com/harry.jpg"
        )
        coEvery { repository.getCharacterDetails(characterId) } returns expectedCharacter

        // When
        val result = getCharacterDetailsUseCase(characterId)

        // Then
        assertEquals(expectedCharacter, result)
        coVerify(exactly = 1) { repository.getCharacterDetails(characterId) }
    }

    @Test
    fun `given invalid character ID when getting character details then return null`() = runBlocking {
        // Given
        val characterId = "999"
        coEvery { repository.getCharacterDetails(characterId) } returns null

        // When
        val result = getCharacterDetailsUseCase(characterId)

        // Then
        assertEquals(null, result)
        coVerify(exactly = 1) { repository.getCharacterDetails(characterId) }
    }
}