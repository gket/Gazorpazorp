package com.gketdev.gazorpazorp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "origin")
@JsonClass(generateAdapter = true)
data class Origin(
    @Json(name = "name")
    var originName: String,
    @Json(name = "url")
    @PrimaryKey
    var originUrl: String
)