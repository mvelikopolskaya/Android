package com.example.myapplication


import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET



private const val BASE_URL = "https://randomuser.me/api/"

object RetrofitServices {
    private val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(MoshiConverterFactory.create())
                            .build()

    val searchUserApi : UserSearchApi = retrofit.create(UserSearchApi ::class.java)
}

interface UserSearchApi {
    @GET("/api/?noinfo/")
    suspend fun getUser(): Response<UserModel>
}


