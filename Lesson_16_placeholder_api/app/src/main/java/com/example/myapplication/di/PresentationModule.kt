package com.example.myapplication.di

import com.example.myapplication.domain.GetPostUseCase
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun providesMainViewModel(getPostUseCase: GetPostUseCase): MainViewModel{
        return MainViewModel(getPostUseCase)
    }

    @Provides
    fun providesMainViewModelFactory(mainViewModel: MainViewModel): MainViewModelFactory{
        return MainViewModelFactory(mainViewModel)
    }
}