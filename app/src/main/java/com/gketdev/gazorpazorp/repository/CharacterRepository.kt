package com.gketdev.gazorpazorp.repository

import com.gketdev.gazorpazorp.api.RickAndMortyApiService
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.data.CharacterResponse
import com.gketdev.gazorpazorp.database.CharacterDao
import com.gketdev.gazorpazorp.network.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(private val apiService: RickAndMortyApiService) : BaseRepository() {
    fun getAllCharacters(): Flow<NetworkState<CharacterResponse>> = flow {
        emit(apiCallResponse { apiService.getAllCharacters() })
    }
}