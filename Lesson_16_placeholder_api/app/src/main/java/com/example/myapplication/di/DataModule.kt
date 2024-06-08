package com.example.myapplication.di

import com.example.myapplication.data.PostDto
import com.example.myapplication.data.PostRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun providesPostDto(): PostDto{
        return PostDto("", "", "", "")
    }

    @Provides
    fun providesPostRepository(postDto: PostDto): PostRepository{
        return PostRepository(postDto)
    }
}