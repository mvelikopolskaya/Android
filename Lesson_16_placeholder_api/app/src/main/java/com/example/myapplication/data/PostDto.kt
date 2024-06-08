package com.example.myapplication.data

import com.example.myapplication.entity.Post
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class PostDto(
    @Json(name = "userId") override var userId: String,
    @Json(name = "id") override var id: String,
    @Json(name = "title") override var title: String,
    @Json(name = "body") override var body: String
) : Post