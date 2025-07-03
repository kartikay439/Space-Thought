package com.example.space.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.business.ApiResponse
import com.example.business.usecase.IsLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppNavigationViewModel(isLoginUseCase: IsLoginUseCase) : ViewModel() {
    private val _isLogin =
        MutableStateFlow<ApiResponse<com.example.business.model.User>>(ApiResponse.Loading)
    val isLogin = _isLogin.asStateFlow()

    init {
        viewModelScope.launch {
            // is login request sent
            _isLogin.value = isLoginUseCase()
            Log.v("tag", _isLogin.value.toString())

        }
    }
}