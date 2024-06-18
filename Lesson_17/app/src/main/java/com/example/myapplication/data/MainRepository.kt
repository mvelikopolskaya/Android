package com.example.myapplication.data


import retrofit2.Response

class MainRepository(private var photoDto: PhotoDto) {
    suspend fun getRoverPhotos(): PhotoDto {
        val response: Response<PhotoDto>?
        response = PhotoSearchApiInterface.photoSearchApiModule().getPhotos()
        if (response.isSuccessful) {
            val item = response.body()?.photos
            if (item != null) {
                photoDto.photos = item
            }
        }
        return photoDto
    }
}

