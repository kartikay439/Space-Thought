package com.example.space.screens.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.business.ApiResponse
import com.example.business.model.PostIdeaResponse
import com.example.data.usecase.UploadIdeaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class UploadViewModel(val uploadIdeaUseCase:UploadIdeaUseCase):ViewModel() {

  private val _response = MutableStateFlow<ApiResponse<PostIdeaResponse>>(ApiResponse.Loading)
  val response = _response.asStateFlow()

  fun uploadIdea(thought:String,thoughtMedia: File){
        viewModelScope.launch {
            _response.value = uploadIdeaUseCase(thought,thoughtMedia)

        }
    }







}