package com.example.space.di

import com.example.business.usecase.GetAllPostUseCase
import com.example.business.usecase.IsLoginUseCase
import com.example.business.usecase.LoginUseCase
import com.example.business.usecase.SignUpUseCase
import com.example.data.usecase.UploadIdeaUseCase
import com.example.space.screens.AppNavigationViewModel
import com.example.space.screens.allPost.AllPostScreenViewModel
import com.example.space.screens.auth.login.LoginScreenViewModel
import com.example.space.screens.auth.signup.SignUpScreenViewModel
import com.example.space.screens.upload.UploadViewModel
import com.example.space.screens.userScreen.UserScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel { AppNavigationViewModel(get()) }
    viewModel { UploadViewModel(get()) }
    viewModel { AllPostScreenViewModel(get()) }
    viewModel {LoginScreenViewModel(get(),get()) }
    viewModel {SignUpScreenViewModel(get())}
    viewModel {UserScreenViewModel(get())}



    single { UploadIdeaUseCase(get()) }
    single { GetAllPostUseCase(get()) }
    single{ SignUpUseCase(get())}

    factory { IsLoginUseCase(get()) }
    single{LoginUseCase(get())}
}