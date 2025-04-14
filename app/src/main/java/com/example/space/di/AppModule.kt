package com.example.space.di

import com.example.business.usecase.GetAllPostUseCase
import com.example.business.usecase.IsLoginUseCase
import com.example.business.usecase.LoginUseCase
import com.example.data.usecase.UploadIdeaUseCase
import com.example.space.screens.allPost.AllPostScreenViewModel
import com.example.space.screens.auth.login.LoginScreenViewModel
import com.example.space.screens.upload.UploadViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel { UploadViewModel(get()) }
    viewModel { AllPostScreenViewModel(get()) }
    viewModel {LoginScreenViewModel(get(),get()) }



    single { UploadIdeaUseCase(get()) }
    single { GetAllPostUseCase(get()) }
    factory { IsLoginUseCase(get()) }
    single{LoginUseCase(get())}
}