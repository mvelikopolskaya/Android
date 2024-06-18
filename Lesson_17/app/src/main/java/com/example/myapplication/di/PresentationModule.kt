package com.example.myapplication.di

import com.example.myapplication.domain.GetPhotoUseCase
import com.example.myapplication.presentation.MainViewModel
import com.example.myapplication.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun providesMainViewModel(getPhotoUseCase: GetPhotoUseCase): MainViewModel {
        return MainViewModel(getPhotoUseCase)
    }

    @Provides
    fun providesMainViewModelFactory(mainViewModel: MainViewModel): MainViewModelFactory{
        return MainViewModelFactory(mainViewModel)
    }
}