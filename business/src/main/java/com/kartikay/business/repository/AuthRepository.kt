package com.kartikay.business.repository

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse

interface AuthRepository {
    suspend fun isLogin(): ApiResponse<AuthResponse>
    suspend fun login(email: String, password: String): ApiResponse<AuthResponse>
    suspend fun signup(name: String, email: String, password: String): ApiResponse<AuthResponse>
    suspend fun logout(): ApiResponse<AuthResponse>
}

