package com.example.business.repository

import com.example.business.model.AllPost
import com.example.business.model.Post

interface PostRepository {
    suspend fun postIdea(post: Post):Unit
    suspend fun getAllPost():List<AllPost>
}