package com.gketdev.gazorpazorp.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "location")
data class Location(
    @Json(name = "name")
    var locationName: String,
    @Json(name = "url")
    var locationUrl: String,
    @Json(name = "id")
    @PrimaryKey
    var locationId: Int,
    @Json(name = "type")
    var locationType: String,
    @Json(name = "dimension")
    var dimension: String,
    @Json(name = "created")
    var locationCreated: String
)
