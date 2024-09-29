package com.hpgallery.domain.model

data class HpCharacter(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: String?,
    val yearOfBirth: String?,
    val alive: Boolean,
    val image: String?
)