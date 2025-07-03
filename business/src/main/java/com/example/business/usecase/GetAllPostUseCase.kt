package com.example.business.usecase

import com.example.business.model.AllPost
import com.example.business.model.PostsResponse
import com.example.business.repository.PostRepository

class GetAllPostUseCase(private val repository: PostRepository) {

    private val cachedPosts = mutableListOf<AllPost>()
    private var lastCursor: String? = null
    private var isEndReached = false

    suspend operator fun invoke(nextCursor: String?): PostsResponse {
        if (nextCursor == null && cachedPosts.isNotEmpty()) {
            // Return cached on first load
            return PostsResponse(
                post = cachedPosts,
                nextCursor = lastCursor
            )
        }

        if (isEndReached) {
            return PostsResponse(
                post = emptyList(),
                nextCursor = null
            )
        }

        val result = repository.getAllPost(nextCursor)
        cachedPosts.addAll(result.post)
        lastCursor = result.nextCursor
        isEndReached = result.nextCursor == null

        return PostsResponse(
            post = cachedPosts.toList(),
            nextCursor = lastCursor
        )
    }
}
