package com.kartikay.business.usecase.auth_usecase

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.repository.AuthRepository

class SignUpUseCase(val authRepository: AuthRepository){
    suspend operator fun invoke(name:String,email: String,password: String): ApiResponse<AuthResponse> {
        return authRepository.signup(name,email,password)
    }
}