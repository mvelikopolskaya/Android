package com.example.myapplication.domain

import com.example.myapplication.data.PostRepository
import com.example.myapplication.entity.Post
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val postRepository: PostRepository){
    suspend fun execute() : Post {
        return postRepository.getPost()
    }
}