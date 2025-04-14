package com.example.space.screens.upload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.usecase.UploadIdeaUseCase
import kotlinx.coroutines.launch
import java.io.File

class UploadViewModel(val uploadIdeaUseCase:UploadIdeaUseCase):ViewModel() {

  fun uploadIdea(thought:String,thoughtMedia: File){
        viewModelScope.launch {
            uploadIdeaUseCase(thought,thoughtMedia)

        }
    }







}