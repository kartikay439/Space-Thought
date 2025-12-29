package com.kartikay.data.network.request.user

import com.kartikay.data.ServerIp
import com.kartikay.data.model.ResponseJson
import com.kartikay.data.model.user.UserData
import com.kartikay.data.network.request.makeHttpRequest
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod

class UserNetworkRequest(private val client: HttpClient) {
    suspend fun getUser(): ResponseJson<UserData> {
        return makeHttpRequest(
            client,
            "${ServerIp}/api/user/profile",
            HttpMethod.Get
            )
    }
}