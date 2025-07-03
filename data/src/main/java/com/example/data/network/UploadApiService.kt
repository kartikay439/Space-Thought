package com.example.data.network

import com.example.business.ApiResponse
import com.example.business.model.PostIdeaResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApiService {


    @Multipart
    @POST("api/post/upload") // Replace with your actual endpoint
    suspend fun uploadIdea(
        @Header("Authorization") token : String,
        @Part("thought") thought: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<PostIdeaResponse> // Replace `ApiResponse` with your actual response model

    
}
