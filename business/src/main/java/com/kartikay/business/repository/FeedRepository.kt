package com.kartikay.business.repository


import androidx.paging.PagingData
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.post.Post
import com.kartikay.business.model.post.UploadResponse
import kotlinx.coroutines.flow.Flow
import java.io.File

interface FeedRepository {
    fun getFeed(): Flow<PagingData<Post>>
    suspend fun uploadFeed(
        thought: String,
        image: File
    ): ApiResponse<UploadResponse>

}
