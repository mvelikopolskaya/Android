package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.GetPostUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(private val getPostUseCase: GetPostUseCase): ViewModel() {
    private var _post = MutableStateFlow("")
    var post = _post.asStateFlow()
    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    init {
        reloadPost()
    }

    fun reloadPost(){
        viewModelScope.launch {
            try {
                _post.value = getPostUseCase.execute().toString()
            }catch (e: Exception){
                _error.send(e.toString())
            }
        }
    }
}