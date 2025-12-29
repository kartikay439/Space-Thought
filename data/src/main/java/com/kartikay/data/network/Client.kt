package com.kartikay.data.network

import com.kartikay.data.ServerIp
import com.kartikay.data.session.SessionManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val client = module {
    single {
        val sessionManager: SessionManager = get()
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
            install(Logging) { level = LogLevel.ALL }

            install(WebSockets)

            defaultRequest {
                sessionManager.getAuthToken()?.let { token ->
                    header(
                        HttpHeaders.Authorization,
                        "$token"
                    )
                }
            }


        }
    }
//
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = get<SessionManager>().getAuthToken()
                val request = chain.request().newBuilder()
                    .apply {
                        token?.let {
                            addHeader("Authorization", "Bearer $it")
                        }
                    }
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("${ServerIp}/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
//            .create(FeedUploadApi::class.java)
    }


//    single {
//        Retrofit.Builder()
//            .baseUrl("http://${ServerIp}:5000/") // Change to your actual backend URL
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
////            .create(UploadApiService::class.java)
//    }
}