package com.kartikay.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ResponseJson<T>(
    val statusCode: String,
    val data: T? = null,
    val message: String,
    val success: Boolean
) {

}