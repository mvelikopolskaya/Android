package com.example.myapplication

sealed class State {
    object Loading : State()
    object Complete : State()
}