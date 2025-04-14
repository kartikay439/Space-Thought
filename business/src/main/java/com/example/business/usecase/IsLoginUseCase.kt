package com.example.business.usecase

import com.example.business.ApiResponse
import com.example.business.model.User
import com.example.business.repository.AuthRepository

class IsLoginUseCase(val authRepository: AuthRepository) {
    suspend operator fun invoke():ApiResponse<User>{
        return authRepository.isLogin()
    }
}