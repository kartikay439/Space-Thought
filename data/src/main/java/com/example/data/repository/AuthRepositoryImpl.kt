package com.example.data.repository

import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.model.User
import com.example.business.repository.AuthRepository
import com.example.data.network.request.UserNetworkRequest

class AuthRepositoryImpl(val userNetworkRequest: UserNetworkRequest):AuthRepository{
    override suspend fun login(email: String, password: String): ApiResponse<LoginResponse> {
       return userNetworkRequest.login(email,password)
    }

    override suspend fun isLogin(): ApiResponse<User> {
       return userNetworkRequest.isLogin()
    }

    override suspend fun signup(name: String, email: String, password: String) {
        TODO("Not yet implemented")
    }

}