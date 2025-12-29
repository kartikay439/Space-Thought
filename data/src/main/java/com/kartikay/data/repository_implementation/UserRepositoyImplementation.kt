package com.kartikay.data.repository_implementation

import android.util.Log
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.user.User
import com.kartikay.business.repository.UserRepository
import com.kartikay.data.network.request.user.UserNetworkRequest

class UserRepositoyImplementation(private val userNetworkRequest: UserNetworkRequest) :
    UserRepository {
    override suspend fun getUser(): ApiResponse<User> {
        try {
            Log.v("Inside user repo", "Inside user repo")
            val response = userNetworkRequest.getUser()
            if (response.success) {
                return ApiResponse.Success<User>(User(response.data!!.name, response.data!!.email))
            } else {
                return ApiResponse.Error(response.message)
            }
        } catch (e: Exception) {
            return ApiResponse.Error(e.message.toString())
        }
    }
}