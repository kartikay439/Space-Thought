package com.kartikay.space.screens


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.usecase.auth_usecase.IsLoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppNavigationViewModel(
    private val isLoginUseCase: IsLoginUseCase
) : ViewModel() {

    private val _response = MutableStateFlow<ApiResponse<AuthResponse>>(ApiResponse.Loading)
    val response = _response.asStateFlow()

    init {
        viewModelScope.launch {
            _response.value = ApiResponse.Loading

            // This assumes IsLoginUseCase has a suspend operator fun invoke()
            val result = isLoginUseCase()
            _response.value = result

            Log.v("AppNavigationVM", result.toString())
        }
    }

}
