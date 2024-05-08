package com.example.myapplication


import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException


class MainViewModel : ViewModel() {
    private var job : Job? = null
    private val _state = MutableStateFlow<State>(State.Init)
    val state : StateFlow<State> = _state.asStateFlow()

    private val _searchRequest = MutableStateFlow(SearchRequest())
    val searchRequest = _searchRequest.asStateFlow()


    fun clearRequest() {
        _state.value = State.Init
        searchRequest.value.result = ""
        searchRequest.value.query = ""
    }

    fun onTextChanged(view : View, request : String) {
        var result : String
        if (request.length >= 3) {
            job?.cancel()
            job = viewModelScope.launch {
                try{
                    delay(300)
                    _state.value = State.Loading
                    delay(3000)
                    _state.value = State.Complete(request)
                    result = "Nothing found for $request"
                    searchRequest.value.result = result
                } catch (e :  CancellationException) {
                    clearRequest()
                    e.printStackTrace()
                }
            }
        } else {
            job?.cancel()
            clearRequest()
        }
    }
}