package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.PhotoDto
import com.example.myapplication.domain.GetPhotoUseCase
import com.example.myapplication.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel (
    private val getPhotoUseCase: GetPhotoUseCase
) : ViewModel() {
    private var _photos: MutableStateFlow<PhotoDto?> = MutableStateFlow(null)
    var photos = _photos.asStateFlow()
    private var _state = MutableStateFlow<State>(State.Complete)
    var state = _state.asStateFlow()


    fun loadPhotos(){
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _state.value = State.Loading
                getPhotoUseCase.execute()
            }.fold(
                onSuccess = {
                    _state.value = State.Complete
                    _photos.value = it },
                onFailure = { Log.d("msg", it.message.toString())}
            )
        }
    }

    fun refresh() {
        loadPhotos()
    }
}