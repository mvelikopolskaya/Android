package com.example.myapplication.di


import com.example.myapplication.data.MainRepository
import com.example.myapplication.data.PhotoDto
import dagger.Module
import dagger.Provides

@Module
class DataModule {
    @Provides
    fun provideMainRepository(photos: PhotoDto): MainRepository {
        return MainRepository(photos)
    }
    @Provides
    fun providePhotos(): PhotoDto{
        return PhotoDto(null)
    }
}