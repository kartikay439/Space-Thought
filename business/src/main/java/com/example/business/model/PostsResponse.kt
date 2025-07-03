package com.example.business.model

data class PostsResponse(
    val post: List<AllPost>,
    val nextCursor: String?
)
