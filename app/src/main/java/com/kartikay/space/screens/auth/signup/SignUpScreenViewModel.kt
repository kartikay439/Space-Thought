package com.kartikay.space.screens.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.usecase.auth_usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpScreenViewModel(val signUpUseCase: SignUpUseCase):ViewModel() {
    private val response  = MutableStateFlow<ApiResponse<AuthResponse>>(ApiResponse.Loading)
    val response_ = response.asStateFlow()

    suspend fun signUp(name:String,email: String,password: String){
        viewModelScope.launch {
          response.value =  signUpUseCase(name,email,password)
        }
    }
}