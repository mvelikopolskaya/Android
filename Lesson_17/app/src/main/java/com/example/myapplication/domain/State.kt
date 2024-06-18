package com.example.myapplication.domain

sealed class State {
    object Loading: State()
    object Complete: State()
}