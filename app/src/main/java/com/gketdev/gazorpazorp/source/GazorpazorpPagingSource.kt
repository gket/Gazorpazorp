package com.gketdev.gazorpazorp.source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.gketdev.gazorpazorp.api.RickAndMortyApiService
import com.gketdev.gazorpazorp.data.Character
import com.gketdev.gazorpazorp.data.CharacterKeys
import com.gketdev.gazorpazorp.database.RickAndMortyDatabase
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GazorpazorpPagingSource(
    private val query: String,
    private val networkService: RickAndMortyApiService,
    private val database: RickAndMortyDatabase
) : RemoteMediator<Int, Character>() {

    private val RICK_AND_MORTY_START_PAGE_INDEX = 1

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: RICK_AND_MORTY_START_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getCharacterKeyFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey
            }
            LoadType.APPEND -> {
                val characterKeys = getCharacterKeyLastItem(state)
                val nextKey = characterKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = characterKeys != null)
                nextKey
            }
        }

        try {
            val apiResponse = networkService.getAllCharacters(page)

            val characters = apiResponse.body()?.results

            val endOfPaginationReached = characters?.isEmpty()
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.characterKeysDao().clearCharacterKeys()
                    database.characterDao().clearCharacters()
                }
                val prevKey = if (page == RICK_AND_MORTY_START_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached == true) null else page + 1
                val keys = characters?.map {
                    CharacterKeys(
                        characterKeyId = it.characterId,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                if (keys != null) {
                    database.characterKeysDao().insertAll(keys)
                    Log.d("CHARS::", "INSERTED Keys")
                }
                if (characters != null) {
                    database.characterDao().insertAllCharacters(characters)
                    Log.d("CHARS::", "INSERTED Chars")
                }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached == true)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getCharacterKeyLastItem(state: PagingState<Int, Character>): CharacterKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                database.characterKeysDao().remoteCharacterKeysId(character.characterId)
            }
    }

    private suspend fun getCharacterKeyFirstItem(state: PagingState<Int, Character>): CharacterKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                database.characterKeysDao().remoteCharacterKeysId(character.characterId)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): CharacterKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.characterId?.let { characterId ->
                database.characterKeysDao().remoteCharacterKeysId(characterId)
            }
        }
    }


}