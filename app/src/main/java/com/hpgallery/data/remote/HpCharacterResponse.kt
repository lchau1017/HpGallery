package com.hpgallery.data.remote

data class HpCharacterResponse(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: String?,
    val dateOfBirth: String?,
    val isAlive: Boolean,
    val imageUrl: String?
)