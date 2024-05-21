package com.example.myapplication

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserModel(
    @Json(name="results") var results: List<User>
)

@JsonClass(generateAdapter = true)
data class User(
    @Json(name="gender") val gender: String?,
    @Json(name="name") val name: Name,
    @Json(name="location") val location: Location,
    @Json(name="email") val email: String?,
    @Json(name="login") val login: Login,
    @Json(name="dob") val dob: Dob,
    @Json(name="registered") val registered: Registered,
    @Json(name="phone") val phone: String?,
    @Json(name="cell") val cell: String?,
    @Json(name="id") val id: ID,
    @Json(name="picture") val picture: Picture,
    @Json(name="nat") val nat: String?
)

@JsonClass(generateAdapter = true)
data class Picture(
    @Json(name="large") val large: String?,
    @Json(name="medium") val medium : String?,
    @Json(name="thumbnail") val thumbnail: String?
)

@JsonClass(generateAdapter = true)
data class ID (
    @Json(name="name") val name: String?,
    @Json(name="value") val value: String?
)

@JsonClass(generateAdapter = true)
data class Registered (
    @Json(name="date") val date: String?,
    @Json(name="age") val age: Int?
)

@JsonClass(generateAdapter = true)
data class Dob (
    @Json(name="date") val date: String?,
    @Json(name="age") val age: Int?
)

@JsonClass(generateAdapter = true)
data class Login (
    @Json(name="uuid") val uuid: String?,
    @Json(name="username") val username: String?,
    @Json(name="password") val password: String?,
    @Json(name="salt") val salt: String?,
    @Json(name="md5") val md5: String?,
    @Json(name="sha1")val sha1: String?,
    @Json(name="sha256") val sha256: String?
)

@JsonClass(generateAdapter = true)
data class Location (
    @Json(name="street") val street: Street,
    @Json(name="city") val city: String?,
    @Json(name="state") val state: String?,
    @Json(name="country") val country: String?,
    @Json(name="postcode") val postcode: String?,
    @Json(name="coordinates") val coordinate: Coordinate,
    @Json(name="timezone") val timezone: Timezone
)

@JsonClass(generateAdapter = true)
data class Street (
    @Json(name="number") val number: Int?,
    @Json(name="name") val name: String?
)

@JsonClass(generateAdapter = true)
data class Coordinate (
    @Json(name="latitude") val latitude: String?,
    @Json(name="longitude") val longitude: String?
)

@JsonClass(generateAdapter = true)
data class Timezone(
    @Json(name="offset") val offset: String?,
    @Json(name="description") val description: String?
)

@JsonClass(generateAdapter = true)
data class Name (
    @Json(name="title") val title: String?,
    @Json(name="first") val first: String?,
    @Json(name="last") val last: String?
)

