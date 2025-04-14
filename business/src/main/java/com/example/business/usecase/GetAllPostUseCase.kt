package com.example.business.usecase

import com.example.business.model.AllPost
import com.example.business.repository.PostRepository

class GetAllPostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(): List<AllPost> {
        return repository.getAllPost()

    }
}