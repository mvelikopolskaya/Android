package com.example.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Complete)
    val state : StateFlow<State> = _state.asStateFlow()

    fun onButtonClick(searchRequest: String, context: Context) {
        viewModelScope.launch {
            _state.value = State.Loading
            delay(3000)
            _state.value = State.Complete
        }
    }
}