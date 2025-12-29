package com.kartikay.data.network.request.auth

import com.kartikay.data.ServerIp
import com.kartikay.data.model.ResponseJson
import com.kartikay.data.model.auth.AuthData
import com.kartikay.data.model.auth.LoginUser
import com.kartikay.data.model.auth.SignUpUser
import com.kartikay.data.network.request.makeHttpRequest
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class AuthNetworkRequest(
    private val client: HttpClient,
) {

    suspend fun isLogin(): ResponseJson<AuthData> {
        val response = makeHttpRequest<ResponseJson<AuthData>>(
            client = client,
            url = "${ServerIp}/api/auth/isLogin",
            method = HttpMethod.Get,

            )
        return response
    }


    suspend fun login(
        email: String,
        password: String
    ): ResponseJson<AuthData> {

        val response = makeHttpRequest<ResponseJson<AuthData>>(
            client = client,
            url = "${ServerIp}/api/auth/login",
            method = HttpMethod.Companion.Post,
            body = LoginUser(email, password)
        )


        return response
    }


    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): ResponseJson<AuthData> {
        val response = makeHttpRequest<ResponseJson<AuthData>>(
            client = client,
            url = "${ServerIp}/api/auth/signup",
            method = HttpMethod.Companion.Post,
            body = SignUpUser(name, email, password)
        )
        return response
    }
}