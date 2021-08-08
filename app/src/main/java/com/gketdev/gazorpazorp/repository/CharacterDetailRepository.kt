package com.gketdev.gazorpazorp.repository

import com.gketdev.gazorpazorp.api.RickAndMortyApiService
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.database.RickAndMortyDatabase
import com.gketdev.gazorpazorp.network.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class CharacterDetailRepository(
    private val apiService: RickAndMortyApiService,
    private val database: RickAndMortyDatabase
) : BaseRepository() {

    fun getSingleCharWithId(id: Int): Flow<DataState<Character>> = flow {
        database.characterDao().getCharacterById(id).collect {
            if (it != null) {
                emit(DataState.Success(it))
            } else {
                emit(DataState.Error(null, 0, "Database error"))
            }
        }
    }

}