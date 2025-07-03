package com.example.data.repository

import NetworkRequests
import android.util.Log
import com.example.business.ApiResponse
import com.example.business.model.AllPost
import com.example.business.model.Post
import com.example.business.model.PostIdeaResponse
import com.example.business.model.PostsResponse
import com.example.business.repository.PostRepository

class PostRepositoryImpl(val networkRequests: NetworkRequests):PostRepository {
    override suspend fun postIdea(post: Post):ApiResponse<PostIdeaResponse> {
            return networkRequests.postIdea(post)
    }

    override suspend fun getAllPost(nextCursor:String?):PostsResponse {
        try {
            return networkRequests.getAllPost(nextCursor)
        }catch (
            exception : Exception
        ){
            Log.v("tag",exception.message.toString())
            return PostsResponse(emptyList<AllPost>(),null)
        }

    }
}