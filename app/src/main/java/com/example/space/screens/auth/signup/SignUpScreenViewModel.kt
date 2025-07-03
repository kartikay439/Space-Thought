package com.example.space.screens.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.business.ApiResponse
import com.example.business.model.SignUpResponse
import com.example.business.usecase.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpScreenViewModel(val signUpUseCase: SignUpUseCase):ViewModel() {
    private val response  = MutableStateFlow<ApiResponse<SignUpResponse>>(ApiResponse.Loading)
    val response_ = response.asStateFlow()

    suspend fun signUp(name:String,email: String,password: String){
        viewModelScope.launch {
          response.value =  signUpUseCase(name,email,password)
        }
    }
}