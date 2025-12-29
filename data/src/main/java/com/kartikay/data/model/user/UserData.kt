package com.kartikay.data.model.user

import kotlinx.serialization.Serializable


@Serializable
data class UserData(
    val name: String,
    val email: String
)