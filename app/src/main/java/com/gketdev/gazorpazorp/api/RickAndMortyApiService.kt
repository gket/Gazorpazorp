package com.gketdev.gazorpazorp.api

import com.gketdev.gazorpazorp.data.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApiService {
    @GET("/api/character")
    suspend fun getAllCharacters(): Response<CharacterResponse>
}