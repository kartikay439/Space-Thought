package com.example.business.usecase

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.repository.AuthRepository

class LoginUseCase(val authRepository: AuthRepository) {

    suspend operator fun invoke(email:String,password:String):ApiResponse<LoginResponse>{
        return authRepository.login(email,password)
    }

}