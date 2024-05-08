package com.example.myapplication

sealed class State {
    data object Init : State()
    data object Loading : State()
    data class Complete(val result: String) : State()
}