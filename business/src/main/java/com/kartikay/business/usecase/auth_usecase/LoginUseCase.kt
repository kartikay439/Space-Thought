package com.kartikay.business.usecase.auth_usecase

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.repository.AuthRepository

class LoginUseCase(val authRepository: AuthRepository) {

    // Login
    suspend operator fun invoke(email: String, password: String): ApiResponse<AuthResponse> {
        return authRepository.login(email, password)
    }

}