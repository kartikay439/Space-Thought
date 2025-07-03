package com.example.space.screens.allPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.business.model.PostsResponse
import com.example.business.usecase.GetAllPostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllPostScreenViewModel(
    private val getAllPostUseCase: GetAllPostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PostsResponse?>(null)
    val state = _state.asStateFlow()

    private var isLoading = false
    private var nextCursor: String? = null
    private var endReached = false

    init {
        getAllPost()
    }

    fun getAllPost() {
        if (isLoading || endReached) return

        isLoading = true

        viewModelScope.launch {
            val response = getAllPostUseCase(nextCursor)

            val currentPosts = _state.value?.post ?: emptyList()
            val updatedPosts = currentPosts + response.post

            _state.value = PostsResponse(
                post = updatedPosts,
                nextCursor = response.nextCursor
            )

            nextCursor = response.nextCursor
            endReached = response.nextCursor == null
            isLoading = false
        }
    }
}
