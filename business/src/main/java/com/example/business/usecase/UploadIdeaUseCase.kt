package com.example.data.usecase

import com.example.business.model.Post
import com.example.business.repository.PostRepository
import java.io.File

class UploadIdeaUseCase(val postRepository: PostRepository) {
    suspend operator fun invoke(thought: String, thoughtMedia: File): Unit {
        return postRepository.postIdea(Post(thought,thoughtMedia))
    }
}