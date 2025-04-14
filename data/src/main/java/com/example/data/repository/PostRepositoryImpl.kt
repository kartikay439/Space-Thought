package com.example.data.repository

import NetworkRequests
import android.util.Log
import com.example.business.model.AllPost
import com.example.business.model.Post
import com.example.business.repository.PostRepository
import io.ktor.http.headers

class PostRepositoryImpl(val networkRequests: NetworkRequests):PostRepository {
    override suspend fun postIdea(post: Post) {
            return networkRequests.postIdea(post)
    }

    override suspend fun getAllPost(): List<AllPost> {
        try {
            return networkRequests.getAllPost().map {

                it.toAllPost()
            }
        }catch (
            exception : Exception
        ){
            Log.v("tag",exception.message.toString())
            return emptyList()
        }

    }
}