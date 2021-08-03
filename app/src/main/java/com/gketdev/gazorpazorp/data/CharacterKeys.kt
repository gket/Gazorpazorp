package com.gketdev.gazorpazorp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "character_keys")
data class CharacterKeys(
    @PrimaryKey
    var characterKeyId : Int,
    var prevKey : Int?,
    var nextKey : Int?
)