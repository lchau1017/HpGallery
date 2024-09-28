package com.hpgallery.domain.usecase

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetHpCharacterDetailsUseCaseTest {

    private lateinit var repository: HpCharacterRepository
    private lateinit var getHpCharacterDetailsUseCase: GetHpCharacterDetailsUseCase

    @Before
    fun setUp() {
        repository = mockk()
        getHpCharacterDetailsUseCase = GetHpCharacterDetailsUseCase(repository)
    }

    @Test
    fun `given character details exist when invoke is called then return character details`() =
        runTest {
            // Given
            val characterId = "1"
            val mockCharacter = HpCharacter(
                id = characterId,
                name = "Harry Potter",
                house = "Gryffindor",
                actor = "Daniel Radcliffe",
                species = "Human",
                dateOfBirth = "19-09-1979",
                isAlive = true,
                imageUrl = null
            )
            coEvery { repository.getCharacterDetails(characterId) } returns mockCharacter

            // When
            val result = getHpCharacterDetailsUseCase(characterId).first()

            // Then
            assertEquals(mockCharacter, result)
        }

    @Test
    fun `given character details do not exist when invoke is called then return null`() = runTest {
        // Given
        val characterId = "2"
        coEvery { repository.getCharacterDetails(characterId) } returns null

        // When
        val result = getHpCharacterDetailsUseCase(characterId).first()

        // Then
        assertEquals(null, result)
    }
}