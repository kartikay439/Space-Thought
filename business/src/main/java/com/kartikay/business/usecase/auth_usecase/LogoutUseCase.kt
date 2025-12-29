package com.kartikay.business.usecase.auth_usecase

import com.kartikay.business.repository.AuthRepository

class LogoutUseCase(val authRepository: AuthRepository) {

    // Logout
    suspend operator fun invoke(){
        authRepository.logout()

    }

}