package com.example.data.network

import com.example.data.ServerIp
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.logger.Logger
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val client = module {
    single {
        HttpClient(OkHttp){
            install(ContentNegotiation){
                json(Json{
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) { level = LogLevel.ALL }


        }
    }

    single {
        Retrofit.Builder()
            .baseUrl("http://${ServerIp}:5000/") // Change to your actual backend URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UploadApiService::class.java)
    }
}