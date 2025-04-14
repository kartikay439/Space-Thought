package com.example.business.repository

import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.model.User

interface AuthRepository {
    suspend fun login(email: String,password:String):ApiResponse<LoginResponse>
    suspend fun isLogin():ApiResponse<User>
    suspend fun signup(name:String,email: String,password: String):Unit
}

