package com.example.myapplication.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo (
    @Json(name="id") var id: String,
    @Json(name="sol") var sol:String,
    @Json(name="camera") var camera: RoverCamera,
    @Json(name="img_src") var imgSrc: String,
    @Json(name="earth_date") var earthDate: String,
    @Json(name="rover") var rover: Rover
)

@JsonClass(generateAdapter = true)
data class Cameras (
    @Json(name="name")var name: String,
    @Json(name="full_name")var fullName: String
)

@JsonClass(generateAdapter = true)
data class Rover(
    @Json(name="id")var id: Int,
    @Json(name="name")var name: String,
    @Json(name="landing_date")var landingDate: String,
    @Json(name="launch_date")var launchDate: String,
    @Json(name ="status")var status: String,
    @Json(name="max_sol")var maxSol: Int,
    @Json(name="max_date")var maxDate: String,
    @Json(name="total_photos")var totalPhotos: Long,
    @Json(name="cameras")var cameras: List<Cameras>
)

@JsonClass(generateAdapter = true)
data class RoverCamera (
    @Json(name="id")var id: Int,
    @Json(name="name")var name: String,
    @Json(name="rover_id")var roverId: Int,
    @Json(name="full_name")var fullName: String
)
