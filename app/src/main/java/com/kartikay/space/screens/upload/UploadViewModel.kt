package com.kartikay.space.screens.upload


import UploadFeedUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.post.UploadResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class UploadViewModel(
    private val uploadFeedUseCase: UploadFeedUseCase
) : ViewModel() {

    private val _uploadState =
        MutableStateFlow<ApiResponse<UploadResponse>>(ApiResponse.Initiate)

    val uploadState = _uploadState.asStateFlow()

    fun uploadFeed(
        thought: String,
        image: File
    ) {
        viewModelScope.launch {
            _uploadState.value = ApiResponse.Loading
            _uploadState.value = uploadFeedUseCase(thought, image)
        }
    }
}
