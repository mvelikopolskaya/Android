package com.example.myapplication.di

import com.example.myapplication.data.PostRepository
import com.example.myapplication.domain.GetPostUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun providesGetPostGetUseCase(postRepository: PostRepository) : GetPostUseCase{
        return GetPostUseCase(postRepository)
    }
}