package com.kartikay.space.screens.userScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.user.User
import com.kartikay.business.usecase.user.GetUserUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserScreenViewModel(getUserUsecase: GetUserUsecase) : ViewModel() {
    private val _user = MutableStateFlow<ApiResponse<User>>(ApiResponse.Loading)
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
           _user.value = getUserUsecase()
        }
    }

}