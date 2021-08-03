package com.gketdev.gazorpazorp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gketdev.gazorpazorp.data.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characterList: List<Character>)

    @Query(
        "SELECT * FROM character WHERE " +
                "characterName LIKE :queryString"
    )

    fun charactersByName(queryString: String): PagingSource<Int, Character>

    @Query("DELETE FROM character")
    suspend fun clearCharacters()
}