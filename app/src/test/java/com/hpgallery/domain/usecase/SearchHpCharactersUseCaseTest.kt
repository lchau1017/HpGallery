package com.hpgallery.domain.usecase


import com.hpgallery.domain.model.HpCharacter
import com.hpgallery.domain.repository.HpCharacterRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SearchHpCharactersUseCaseTest {

    private lateinit var searchHpCharactersUseCase: SearchHpCharactersUseCase
    private lateinit var repository: HpCharacterRepository

    @Before
    fun setUp() {
        repository = mockk()
        searchHpCharactersUseCase = SearchHpCharactersUseCase(repository)
    }

    @Test
    fun `given characters list when searching with matching query then return filtered list`() = runBlocking {
        // Given
        val query = "Harry"
        val characterList = listOf(
            HpCharacter(id = "1", name = "Harry Potter", actor = "Daniel Radcliffe", species = "Human", house = "Gryffindor", dateOfBirth = "31-07-1980", isAlive = true, imageUrl = null),
            HpCharacter(id = "2", name = "Hermione Granger", actor = "Emma Watson", species = "Human", house = "Gryffindor", dateOfBirth = "19-09-1979", isAlive = true, imageUrl = null)
        )
        coEvery { repository.getHpCharacters() } returns characterList

        // When
        val result = searchHpCharactersUseCase(query)

        // Then
        assertEquals(1, result.size)
        assertEquals("Harry Potter", result[0].name)
        coVerify(exactly = 1) { repository.getHpCharacters() }
    }

    @Test
    fun `given characters list when searching with non-matching query then return empty list`() = runBlocking {
        // Given
        val query = "Voldemort"
        val characterList = listOf(
            HpCharacter(id = "1", name = "Harry Potter", actor = "Daniel Radcliffe", species = "Human", house = "Gryffindor", dateOfBirth = "31-07-1980", isAlive = true, imageUrl = null),
            HpCharacter(id = "2", name = "Hermione Granger", actor = "Emma Watson", species = "Human", house = "Gryffindor", dateOfBirth = "19-09-1979", isAlive = true, imageUrl = null)
        )
        coEvery { repository.getHpCharacters() } returns characterList

        // When
        val result = searchHpCharactersUseCase(query)

        // Then
        assertEquals(0, result.size)
        coVerify(exactly = 1) { repository.getHpCharacters() }
    }
}