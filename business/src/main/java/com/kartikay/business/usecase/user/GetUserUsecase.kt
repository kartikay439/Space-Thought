package com.kartikay.business.usecase.user

import com.kartikay.business.ApiResponse
import com.kartikay.business.model.user.User
import com.kartikay.business.repository.UserRepository

class GetUserUsecase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): ApiResponse<User> = userRepository.getUser()
}