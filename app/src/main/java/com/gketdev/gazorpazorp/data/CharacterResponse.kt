package com.gketdev.gazorpazorp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "info")
    val info: PageInfo?,
    @Json(name = "results")
    val results: List<Character>?
)