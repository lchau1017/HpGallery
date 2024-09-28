package com.hpgallery.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HpCharacterDao {
    @Query("SELECT * FROM hpCharacters")
    suspend fun getAllCharacters(): List<HpCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<HpCharacterEntity>)
}