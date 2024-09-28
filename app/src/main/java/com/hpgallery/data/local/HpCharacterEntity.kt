package com.hpgallery.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hpCharacters")
data class HpCharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: String?,
    val dateOfBirth: String?,
    val isAlive: Boolean,
    val imageUrl: String?
)