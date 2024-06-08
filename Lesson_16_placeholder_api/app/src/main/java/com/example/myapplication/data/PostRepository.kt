package com.example.myapplication.data

import com.example.myapplication.entity.Post
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject



class PostRepository @Inject constructor(private val postDto: PostDto) {
    private var count = 0

    suspend fun getPost(): Post {
        count = (1.. 100).random()
        val response = PostModule.providePostService().getPost(count.toString())
        val item = response.body()
        if (response.isSuccessful) {
            if (item != null) {
                postDto.userId = item.userId
                postDto.id = item.id
                postDto.title = item.title
                postDto.body = item.body
            }
        }
        return postDto
    }
}

object RetrofitService {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

object PostModule {
    fun providePostService(): PostSearchApiInterface =
        RetrofitService.retrofit.create(PostSearchApiInterface::class.java)
}

interface PostSearchApiInterface{
    @GET("/posts/{id}")
    suspend fun getPost(@Path("id") id: String): Response<PostDto>
}