package com.kartikay.data.repository_implementation

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.repository.AuthRepository
import com.kartikay.data.network.request.auth.AuthNetworkRequest
import com.kartikay.data.session.SessionManager

class AuthRepositoryImpl(
    val authNetworkRequest: AuthNetworkRequest,
    val sessionManager: SessionManager
) : AuthRepository {


    override suspend fun login(email: String, password: String): ApiResponse<AuthResponse> {
        try {
            val response = authNetworkRequest.login(email, password)

            return if (response.success) {
                sessionManager.addAuthToken(response.data?.token)
                ApiResponse.Success(AuthResponse(response.message,response.success))
            } else {
                ApiResponse.Error(response.message)
            }

        } catch (e: Exception) {

            return ApiResponse.Error(e.message.toString())

        }
    }

    override suspend fun isLogin(): ApiResponse<AuthResponse> {
        try {
            val response = authNetworkRequest.isLogin()

            return  if(response.success){
                ApiResponse.Success(AuthResponse(response.message,response.success))
            }else{
                ApiResponse.Error(response.message)
            }

        }catch (e: Exception){

            return ApiResponse.Error(e.message.toString())

        }
    }

    override suspend fun signup(
        name: String,
        email: String,
        password: String
    ): ApiResponse<AuthResponse> {
        try {
            val response = authNetworkRequest.signUp(name, email, password)

            return if (response.success) {
                sessionManager.addAuthToken(response.data?.token)
                ApiResponse.Success(AuthResponse(response.message,response.success))
            } else {
                ApiResponse.Error(response.message)
            }

        } catch (e: Exception) {

            return ApiResponse.Error(e.message.toString())

        }
    }

    override suspend fun logout(): ApiResponse<AuthResponse> {
        TODO("Not yet implemented")
    }

}