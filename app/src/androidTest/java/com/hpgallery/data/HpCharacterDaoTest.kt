package com.hpgallery.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.hpgallery.data.local.AppDatabase
import com.hpgallery.data.local.HpCharacterDao
import com.hpgallery.data.local.HpCharacterEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class HpCharacterDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var characterDao: HpCharacterDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        characterDao = database.hpCharacterDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveCharacters() = runBlocking {
        // Given
        val characterEntity = HpCharacterEntity(
            id = "1",
            name = "Harry Potter",
            actor = "Daniel Radcliffe",
            species = "Human",
            house = "Gryffindor",
            yearOfBirth = "31-07-1980",
            alive = true,
            image = "https://example.com/harry.jpg"
        )

        // When
        characterDao.insertAll(listOf(characterEntity))
        val characters = characterDao.getAllCharacters()

        // Then
        assertEquals(1, characters.size)
        assertEquals("Harry Potter", characters[0].name)
    }
}