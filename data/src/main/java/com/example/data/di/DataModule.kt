package com.example.data.di

import NetworkRequests
import com.example.business.repository.AuthRepository
import com.example.business.repository.PostRepository
//import com.example.data.network.NetworkRequests
import com.example.data.network.client
import com.example.data.network.request.UserNetworkRequest
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.PostRepositoryImpl
import com.example.data.session.SessionManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

//import org.koin.dsl.module

val dataModule = module {
    includes(client)
    single {
        NetworkRequests(

           get(),get(),androidContext(),get()
        )
    }

    single {
        UserNetworkRequest(get(),get())
    }

    //Repository
    single<PostRepository> { PostRepositoryImpl(get()) }
    single<AuthRepository>{ AuthRepositoryImpl(get()) }

    single{ SessionManager(androidContext()) }
}