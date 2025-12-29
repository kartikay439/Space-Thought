package com.kartikay.data.network.request.feed


import com.kartikay.data.model.post.UploadDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FeedUploadApi {    @Multipart
    @POST("api/post/upload")
    suspend fun uploadPost(
        @Part("thought") thought: RequestBody,
        @Part thoughtMedia: MultipartBody.Part
    ): Response<UploadDto>
}

