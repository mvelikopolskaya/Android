package com.example.myapplication.di

import com.example.myapplication.data.MainRepository
import com.example.myapplication.domain.GetPhotoUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetPhotoUseCase(mainRepository: MainRepository) : GetPhotoUseCase {
        return GetPhotoUseCase(mainRepository)
    }
}