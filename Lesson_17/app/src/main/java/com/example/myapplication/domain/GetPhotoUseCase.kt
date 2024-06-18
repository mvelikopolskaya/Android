package com.example.myapplication.domain

import com.example.myapplication.data.MainRepository
import com.example.myapplication.data.PhotoDto


class GetPhotoUseCase (private val mainRepository: MainRepository) {
    suspend fun execute(): PhotoDto {
        return mainRepository.getRoverPhotos()
    }
}