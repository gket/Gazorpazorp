package com.gketdev.gazorpazorp.repository

import android.util.Log
import androidx.paging.*
import com.gketdev.gazorpazorp.api.RickAndMortyApiService
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.data.CharacterResponse
import com.gketdev.gazorpazorp.database.CharacterDao
import com.gketdev.gazorpazorp.database.RickAndMortyDatabase
import com.gketdev.gazorpazorp.network.NetworkState
import com.gketdev.gazorpazorp.source.GazorpazorpPagingSource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepository(
    private val apiService: RickAndMortyApiService,
    private val database: RickAndMortyDatabase
) : BaseRepository() {

    fun getCharacters(query: String): Flow<PagingData<Character>> {
        Log.d("RickAndMortyRepository", "New query: $query")

        val dbQuery = "%${query.replace(' ', '%')}%"
        val pagingSourceFactory = { database.characterDao().charactersByName(dbQuery) }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = GazorpazorpPagingSource(
                query,
                apiService,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.cachedIn(GlobalScope)
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}