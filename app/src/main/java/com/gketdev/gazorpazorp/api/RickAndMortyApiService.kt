package com.gketdev.gazorpazorp.api

import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.data.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApiService {
    @GET("/api/character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharacterResponse>

    @GET("/api/character/{id}")
    suspend fun getSingleCharacter(@Path("id") id: Int): Response<Character>
}