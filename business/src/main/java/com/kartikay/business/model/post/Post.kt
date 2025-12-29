package com.kartikay.business.model.post


data class Post(
    val id: String,
    val username: String,
    val thought: String,
    val thoughtMedia: List<String>
)
