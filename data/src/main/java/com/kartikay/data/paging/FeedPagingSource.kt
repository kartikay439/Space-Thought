package com.kartikay.data.paging
// data/paging/FeedPagingSource.kt
// data/paging/FeedPagingSource.kt

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.post.Post
import com.kartikay.data.network.request.feed.FeedNetworkRequest

class FeedPagingSource(
    private val api: FeedNetworkRequest
) : PagingSource<String, Post>() {

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, Post> {

        return when (val result = api.getFeed(params.key)) {

            is ApiResponse.Success -> {
                LoadResult.Page(
                    data = result.data.posts.map { it.toDomain() },
                    prevKey = null,
                    nextKey = result.data.nextCursor
                )
            }

            is ApiResponse.Error -> {
                LoadResult.Error(Exception(result.message))
            }

            ApiResponse.Loading -> {
                LoadResult.Error(Exception("Invalid loading state"))
            }

            is ApiResponse.Initiate -> {
                LoadResult.Error(Exception("Invalid initiate state"))
            }

        }
    }

    override fun getRefreshKey(
        state: PagingState<String, Post>
    ): String? = null
}
