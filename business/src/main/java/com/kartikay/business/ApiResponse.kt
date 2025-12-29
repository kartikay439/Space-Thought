package com.kartikay.business

sealed  class ApiResponse<out T>{
    data object Initiate:ApiResponse<Nothing>()
    data object Loading:ApiResponse<Nothing>()
    data class Success<out T>(val data:T):ApiResponse<T>()
    data class Error(val message:String):ApiResponse<Nothing>()

    // If success -> data
    // else -> message
}
