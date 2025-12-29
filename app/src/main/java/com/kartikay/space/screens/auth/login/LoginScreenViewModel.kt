package com.kartikay.space.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.auth.AuthResponse
import com.kartikay.business.usecase.auth_usecase.IsLoginUseCase
import com.kartikay.business.usecase.auth_usecase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val loginUseCase: LoginUseCase,val isLoginUseCase: IsLoginUseCase):ViewModel() {
   private val response  = MutableStateFlow<ApiResponse<AuthResponse>>(ApiResponse.Loading)



//   private val isLogin  = MutableStateFlow<ApiResponse<User>>(ApiResponse.Loading)
//   val isLogin_  = isLogin.asStateFlow()


    val response_ = response.asStateFlow()




    fun login(email:String,password:String){
        viewModelScope.launch {
            response.value = loginUseCase(email,password)
            Log.v("in viewmodel",response.value.toString())
        }
    }

    fun resetResponse(){
        response.value = ApiResponse.Loading
    }

}

//sealed class Status{
//    object Loading:Status()
//    class LoginResponse:Status()
//    class LoginRespoanseError:Status()
//}