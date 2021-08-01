package com.gketdev.gazorpazorp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "character")
data class Character(
    @Json(name = "id")
    @PrimaryKey
    var characterId: Int,
    @Json(name = "created")
    var characterCreated: String,
    @Json(name = "episode")
    var episode: List<String>,
    @Json(name = "gender")
    var gender: String,
    @Json(name = "image")
    var image: String,
    @Json(name = "name")
    var characterName: String,
    @Json(name = "origin")
    @Embedded var origin: Origin,
    @Json(name = "species")
    var species: String,
    @Json(name = "status")
    var status: String,
    @Json(name = "type")
    var type: String,
    @Json(name = "url")
    var url: String
){

}