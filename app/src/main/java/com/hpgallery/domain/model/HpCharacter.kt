package com.hpgallery.domain.model

data class HpCharacter(
    val id: String, // Optional unique ID if available from the data source
    val name: String,
    val actor: String,
    val species: String,
    val house: String?,
    val dateOfBirth: String?,  // Consider using a `Date` type if you have parsing logic
    val isAlive: Boolean,
    val imageUrl: String?
)