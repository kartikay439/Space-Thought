package com.example.business.usecase

import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.model.SignUpResponse
import com.example.business.repository.AuthRepository

class SignUpUseCase(val authRepository: AuthRepository){
    suspend operator fun invoke(name:String,email: String,password: String):ApiResponse<SignUpResponse>{
        return authRepository.signup(name,email,password)
    }
}