package com.hpgallery.feature.list.viewdata

data class HpCharacterRowViewData(
    val id: String, // Optional unique ID if available from the data source
    val name: String,
    val actor: String,
    val species: String,
    val house: String?,
)