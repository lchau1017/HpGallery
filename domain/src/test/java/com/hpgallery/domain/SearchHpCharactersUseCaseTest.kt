package com.hpgallery.domain

import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import com.hpgallery.domain.usecase.SearchHpCharactersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchHpCharactersUseCaseTest {

    private lateinit var repository: HpCharacterRepository
    private lateinit var searchHpCharactersUseCase: SearchHpCharactersUseCase

    @Before
    fun setUp() {
        repository = mockk()
        searchHpCharactersUseCase = SearchHpCharactersUseCase(repository)
    }

    @Test
    fun `given character list contains matching query when invoke is called then return filtered list`() =
        runTest {
            // Given
            val query = "Harry"
            val mockCharacterList = listOf(
                HpCharacter(
                    id = "1",
                    name = "Harry Potter",
                    house = "Gryffindor",
                    actor = "Daniel Radcliffe",
                    species = "Human",
                    dateOfBirth = "19-09-1979",
                    alive = true,
                    image = null
                ),
                HpCharacter(
                    id = "2",
                    name = "Hermione Granger",
                    house = "Gryffindor",
                    actor = "Emma Watson",
                    species = "Human",
                    dateOfBirth = "19-09-1979",
                    alive = true,
                    image = null
                )
            )
            coEvery { repository.getHpCharacters() } returns mockCharacterList

            // When
            val result = searchHpCharactersUseCase(query).first()

            // Then
            val expectedList = listOf(mockCharacterList[0])
            assertEquals(expectedList, result)
        }

    @Test
    fun `given character list does not contain matching query when invoke is called then return empty list`() =
        runTest {
            // Given
            val query = "Voldemort"
            val mockCharacterList = listOf(
                HpCharacter(
                    id = "1",
                    name = "Harry Potter",
                    house = "Gryffindor",
                    actor = "Daniel Radcliffe",
                    species = "Human",
                    dateOfBirth = "19-09-1979",
                    alive = true,
                    image = null
                ),
                HpCharacter(
                    id = "2",
                    name = "Hermione Granger",
                    house = "Gryffindor",
                    actor = "Emma Watson",
                    species = "Human",
                    dateOfBirth = "19-09-1979",
                    alive = true,
                    image = null
                )
            )
            coEvery { repository.getHpCharacters() } returns mockCharacterList

            // When
            val result = searchHpCharactersUseCase(query).first()

            // Then
            assertEquals(emptyList<HpCharacter>(), result)
        }
}
