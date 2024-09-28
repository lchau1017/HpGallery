package com.hpgallery.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HpCharacterEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hpCharacterDao(): HpCharacterDao
}