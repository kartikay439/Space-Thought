package com.example.data.network.request

import android.util.Log
import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.model.User
import com.example.data.ServerIp
import com.example.data.session.SessionManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.Serializable


class UserNetworkRequest(private val client: HttpClient, val sessionManager: SessionManager) {

    suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
        try {
            val response = client.post("http://${ServerIp}:5000/api/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(email, password))
            }

            Log.v("tag", response.toString())





            if (response.status.value.toString() == "200") {
                val l_r = response.body() as LoginResponseJson
                sessionManager.addAuthToken(l_r.token)
                return ApiResponse.Success(LoginResponse(l_r.token, l_r.message))
                //token will be saved
            }

            return ApiResponse.Error((response.body() as LoginError).message)
        } catch (e: Exception) {
            Log.v("tag", e.message.toString())
            return ApiResponse.Error(e.message.toString())

        }

    }


    suspend fun isLogin(): ApiResponse<User> {
        try {
            val response = client.get("http://${ServerIp}:5000/api/auth/isLogin") {
                header(HttpHeaders.Authorization, sessionManager.getAuthToken())
            }

            if (response.status.value == 200) {
                val user = response.body<com.example.data.network.request.User>()

                return ApiResponse.Success(user.toUser())

            }
            return ApiResponse.Error((response.body() as LoginError).message)
        } catch (e: Exception) {
            return ApiResponse.Error(e.message.toString())
        }
    }


}


@Serializable
data class User(
    val name: String,
    val email: String,
    val password: String
) {
    fun toUser(): User {
        return User(this.name, this.email, this.password)
    }
}


@Serializable
data class SignUpResponseJson(
    val statusCode: String,
    val data: Data,
    val message: String,
    val success: Boolean
)

@Serializable
data class Data(
    val token: String,
)

@Serializable
data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String
)


@Serializable
data class LoginResponseJson(
    val token: String,
    val message: String
) {
    fun toLoginResponse(): LoginResponse {
        return LoginResponse(
            this.token,
            this.message
        )
    }
}

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class LoginError(val message: String)