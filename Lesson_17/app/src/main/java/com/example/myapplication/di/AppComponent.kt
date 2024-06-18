package com.example.myapplication.di

import com.example.myapplication.presentation.MainViewModelFactory
import dagger.Component

@Component(modules = [
    DataModule::class,
    DomainModule::class,
    PresentationModule::class
])
interface AppComponent {
    fun mainViewModelFactory() : MainViewModelFactory
}