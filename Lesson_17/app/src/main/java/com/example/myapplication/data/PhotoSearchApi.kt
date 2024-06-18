package com.example.myapplication.data


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val API_KEY = "L5CPTJfH3WuFRTtjaZXIvv1yOTSlLrzxLWU4wpZU"
private const val SOL = 1000
private const val BASE_URL = "https://api.nasa.gov/"

interface PhotoSearchApi {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getPhotos(
        @Query("sol") sol: Int = SOL,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<PhotoDto>
}

object RetrofitService {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

object PhotoSearchApiInterface {
    fun photoSearchApiModule(): PhotoSearchApi{
        return RetrofitService.retrofit.create(PhotoSearchApi::class.java)
    }
}