package com.gketdev.gazorpazorp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gketdev.gazorpazorp.data.CharacterKeys

@Dao
interface CharacterKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characterKeys: List<CharacterKeys>)

    @Query("SELECT * FROM character_keys WHERE characterKeyId = :characterId")
    suspend fun remoteCharacterKeysId(characterId: Int): CharacterKeys?

    @Query("DELETE FROM character_keys")
    suspend fun clearCharacterKeys()
}