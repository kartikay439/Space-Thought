package com.example.data.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApiService {


    @Multipart
    @POST("api/post/upload") // Replace with your actual endpoint
    suspend fun uploadIdea(
        @Part("thought") thought: RequestBody,
        @Part image: MultipartBody.Part?
    ):Unit // Replace `ApiResponse` with your actual response model

    
}
