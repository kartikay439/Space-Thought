package com.example.data.model

import com.example.business.model.PostsResponse
import kotlinx.serialization.Serializable

@Serializable
data class PostsResponse(
    val posts: List<AllPost>,
    val nextCursor: String?
) {
    fun toBusinessPostResponse(): PostsResponse {
        return PostsResponse(
           posts.map{
               it.toAllPost()
           },
            this.nextCursor
        )
    }
}
