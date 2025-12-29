package com.kartikay.business.repository

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.user.User

interface UserRepository {
    suspend fun getUser(): ApiResponse<User>
}