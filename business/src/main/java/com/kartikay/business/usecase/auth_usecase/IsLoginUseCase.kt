package com.kartikay.business.usecase.auth_usecase

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.repository.AuthRepository

class IsLoginUseCase(val authRepository: AuthRepository) {
    suspend operator fun invoke(): ApiResponse<AuthResponse> {
        return authRepository.isLogin()
    }
}