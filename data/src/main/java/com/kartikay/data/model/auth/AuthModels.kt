package com.kartikay.data.model.auth

import kotlinx.serialization.Serializable

// DTO
@Serializable
data class AuthData(
    val token: String? = null
)


@Serializable
class LoginUser(val email: String, val password: String)

@Serializable
class SignUpUser(val name: String, val email: String, val password: String)