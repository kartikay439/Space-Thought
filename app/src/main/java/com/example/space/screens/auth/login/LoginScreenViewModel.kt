package com.example.space.screens.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.model.User
import com.example.business.usecase.IsLoginUseCase
import com.example.business.usecase.LoginUseCase
import com.example.data.dataStore.UserData
import io.ktor.util.reflect.instanceOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(private val loginUseCase: LoginUseCase,val isLoginUseCase: IsLoginUseCase):ViewModel() {
   private val response  = MutableStateFlow<ApiResponse<LoginResponse>>(ApiResponse.Loading)
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