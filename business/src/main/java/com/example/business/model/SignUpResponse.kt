package com.example.business.model

data class SignUpResponse(
    val statusCode: String,
    val data: SignUpResponseData,
    val message: String,
    val success: Boolean
)

data class SignUpResponseData(
    val token: String
)
