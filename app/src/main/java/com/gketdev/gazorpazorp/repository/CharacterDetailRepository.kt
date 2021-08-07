package com.gketdev.gazorpazorp.repository

import com.gketdev.gazorpazorp.api.RickAndMortyApiService
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.database.RickAndMortyDatabase
import com.gketdev.gazorpazorp.network.NetworkState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class CharacterDetailRepository(
    private val apiService: RickAndMortyApiService,
    private val database: RickAndMortyDatabase
) : BaseRepository() {

    fun getSingleCharWithId(id: Int): Flow<NetworkState<Character>> = flow {
        database.characterDao().getCharacterById(id).collect {
            if (it != null) {
                emit(NetworkState.Success(it))
            } else {
                emit(apiCallResponse { apiService.getSingleCharacter(id) })
            }
        }
    }

}