package com.kartikay.data.model.post

import com.kartikay.business.model.post.Post
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedResponseDto(
    val posts: List<PostDto>,
    val nextCursor: String?
)

@Serializable
data class PostDto(
    @SerialName("_id")
    val id: String,
    val username: String,
    val thought: String,
    val thoughtMedia: List<String>
){
    fun toDomain() = Post(
        id = id,
        username = username,
        thought = thought,
        thoughtMedia = thoughtMedia
    )
}


@Serializable
data class UploadDto(
    val statusCode: Int,
    val message: String,
    val data: Unit?,
    val success: Boolean
)
