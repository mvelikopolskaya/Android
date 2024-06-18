package com.example.myapplication.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoDto(
    @Json(name="photos") var photos: List<Photo>?
)