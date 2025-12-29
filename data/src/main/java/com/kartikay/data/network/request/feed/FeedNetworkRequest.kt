package com.kartikay.data.network.request.feed

import android.util.Log
import com.kartikay.business.ApiResponse
import com.kartikay.data.ServerIp
import com.kartikay.data.model.post.FeedResponseDto
import com.kartikay.data.model.post.UploadDto
import com.kartikay.data.network.request.makeHttpRequest
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

class FeedNetworkRequest(
    private val client: HttpClient,
    private val retrofit: Retrofit
) {

    private val apiU = retrofit.create(FeedUploadApi::class.java)

    suspend fun uploadFeed(
        t: String,
        m: File
    ): Response<UploadDto> {


        val thought = t.toRequestBody("text/plain".toMediaType())
        val fileBody =
            m.asRequestBody("image/*".toMediaType())

        val mediaPart = MultipartBody.Part.createFormData(
            name = "thoughtMedia",   // MUST match backend
            filename = m.name,
            body = fileBody
        )

        return apiU.uploadPost(thought, mediaPart)
//            Log.v("UPLOAD_API", "Request sent")
    }


    suspend fun getFeed(cursor: String?): ApiResponse<FeedResponseDto> {
        return try {

//            Log.d("FeedApi", "Calling feed API. cursor=$cursor")
            val response: FeedResponseDto =
                makeHttpRequest<FeedResponseDto>(
                    client = client,
                    url = "${ServerIp}/api/post/all",
                    method = HttpMethod.Get,
                    params = mapOf("cursor" to cursor)
                )

//            Log.d("FeedApi", "API success. posts=${response.posts.size}")

            ApiResponse.Success(response)

        } catch (e: Exception) {

            Log.e("FeedApi", "API error", e)

            ApiResponse.Error(e.message ?: "Network error")
        }
    }
}