package com.kartikay.data.di

import com.kartikay.business.repository.AuthRepository
import com.kartikay.business.repository.FeedRepository
import com.kartikay.business.repository.NotificationRepository
import com.kartikay.business.repository.UserRepository
import com.kartikay.data.network.client
import com.kartikay.data.network.notification_socket.NotificationSockerService
import com.kartikay.data.network.request.auth.AuthNetworkRequest
import com.kartikay.data.network.request.feed.FeedNetworkRequest
import com.kartikay.data.network.request.user.UserNetworkRequest
import com.kartikay.data.repository.FeedRepositoryImpl
import com.kartikay.data.repository_implementation.AuthRepositoryImpl
import com.kartikay.data.repository_implementation.NotificationRepositoryImplementation
import com.kartikay.data.repository_implementation.UserRepositoyImplementation
import com.kartikay.data.session.SessionManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val dataModule = module {
    includes(client)
    single {
        //                 ktor          retrofit
        FeedNetworkRequest(get(),get())
    }

    single { AuthNetworkRequest(get()) }
    single { UserNetworkRequest(get()) }
    single { NotificationSockerService(get()) }


    //Repository
    single<FeedRepository> { FeedRepositoryImpl(get(), get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single<UserRepository> { UserRepositoyImplementation(get()) }

    single<NotificationRepository> { NotificationRepositoryImplementation(get()) }

//Session
    single { SessionManager(androidContext()) }
}