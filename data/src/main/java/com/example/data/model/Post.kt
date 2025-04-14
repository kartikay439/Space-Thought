package com.example.data.model

import kotlinx.serialization.Serializable
import java.io.File


@Serializable
data class Post(
    val thought:String,
    val thoughtMedia: Byte
)
