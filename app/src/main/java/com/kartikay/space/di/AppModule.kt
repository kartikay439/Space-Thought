package com.kartikay.space.di

import UploadFeedUseCase
import androidx.compose.ui.platform.LocalContext
import com.kartikay.business.usecase.feed_usecase.GetAllPostFeedUseCase
import com.kartikay.business.usecase.auth_usecase.IsLoginUseCase
import com.kartikay.business.usecase.auth_usecase.LoginUseCase
import com.kartikay.business.usecase.auth_usecase.SignUpUseCase
import com.kartikay.business.usecase.user.GetUserUsecase
import com.kartikay.space.screens.AppNavigationViewModel
import com.kartikay.space.screens.allPost.AllPostScreenViewModel
import com.kartikay.space.screens.auth.login.LoginScreenViewModel
import com.kartikay.space.screens.auth.signup.SignUpScreenViewModel
import com.kartikay.space.screens.notification_permission.NotificationViewModel
import com.kartikay.space.screens.upload.UploadViewModel
import com.kartikay.space.screens.userScreen.UserScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    viewModel { AppNavigationViewModel(get()) }
//    viewModel { UploadViewModel(get()) }
    viewModel { AllPostScreenViewModel(get()) }
    viewModel { LoginScreenViewModel(get(), get()) }
    viewModel { SignUpScreenViewModel(get()) }
//    viewModel { UserScreenViewModel(get()) }
    viewModel { UploadViewModel(get()) }

    viewModel() { NotificationViewModel(get() )}




    single { UploadFeedUseCase(get()) }
    single { GetAllPostFeedUseCase(get()) }
    single { SignUpUseCase(get()) }

    factory { IsLoginUseCase(get()) }
    single { LoginUseCase(get()) }

    single { UserScreenViewModel(get()) }
    single { GetUserUsecase(get()) }
}