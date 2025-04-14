package com.example.space.screens.allPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.business.model.AllPost
import com.example.business.usecase.GetAllPostUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllPostScreenViewModel(private val getAllPostUseCase: GetAllPostUseCase):ViewModel() {
    private val state = MutableStateFlow<List<AllPost>?>(null)
    val state_ = state.asStateFlow()
    init {
        getAllPost()
    }

    private fun getAllPost(){
        viewModelScope.launch {
            state.value = getAllPostUseCase()
        }
    }

}