package com.hpgallery.feature.details.viewdata

data class HpCharacterDetailsCardViewData(
    val id: String,
    val name: String,
    val actor: String,
    val species: String,
    val house: String?,
    val dateOfBirth: String?,
    val image: String?,
    val status: String
)
