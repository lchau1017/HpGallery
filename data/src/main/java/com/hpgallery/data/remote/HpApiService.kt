package com.hpgallery.data.remote

import retrofit2.http.GET

interface HpApiService {
    @GET("characters")
    suspend fun getCharacters(): List<HpCharacterResponse>
}
