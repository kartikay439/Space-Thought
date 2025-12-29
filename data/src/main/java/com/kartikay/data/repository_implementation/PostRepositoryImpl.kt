package com.kartikay.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.post.Post
import com.kartikay.business.model.post.UploadResponse
import com.kartikay.business.repository.FeedRepository
import com.kartikay.data.network.request.feed.FeedNetworkRequest
import com.kartikay.data.paging.FeedPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import java.io.File

class FeedRepositoryImpl(
    private val feedNetworkRequest: FeedNetworkRequest,
    private val retrofit: Retrofit
) : FeedRepository {

    override fun getFeed(): Flow<PagingData<Post>> {
        return Pager<String, Post>(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                FeedPagingSource(feedNetworkRequest) as PagingSource<String, Post>
            }
        ).flow
    }


    override suspend fun uploadFeed(
        t: String,
        m: File
    ): ApiResponse<UploadResponse> {
        return try {
            val response = feedNetworkRequest.uploadFeed(t, m).body()
            if (response?.success ?: false) {

                ApiResponse.Success(UploadResponse(response?.message ?: "Success"))

            } else {
                ApiResponse.Error(
                    response?.message.toString()
                )
            }

        } catch (e: Exception) {

            ApiResponse.Error(
                message = e.localizedMessage ?: "Network error"
            )
        }
    }

}


