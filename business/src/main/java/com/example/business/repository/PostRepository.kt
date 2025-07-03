package com.example.business.repository

import com.example.business.ApiResponse
import com.example.business.model.AllPost
import com.example.business.model.Post
import com.example.business.model.PostIdeaResponse
import com.example.business.model.PostsResponse

interface PostRepository {
    suspend fun postIdea(post: Post): ApiResponse<PostIdeaResponse>
    suspend fun getAllPost(nextCursor:String?): PostsResponse
}