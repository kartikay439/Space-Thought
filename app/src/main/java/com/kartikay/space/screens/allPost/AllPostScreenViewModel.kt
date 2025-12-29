package com.kartikay.space.screens.allPost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kartikay.business.usecase.feed_usecase.GetAllPostFeedUseCase

class AllPostScreenViewModel(
    getAllPostFeedUseCase: GetAllPostFeedUseCase
) : ViewModel() {
    val feed = getAllPostFeedUseCase()
        .cachedIn(viewModelScope)
}
