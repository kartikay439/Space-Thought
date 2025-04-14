import android.content.Context
import android.util.Log
import com.example.business.model.Post
import com.example.data.ServerIp
import com.example.data.model.AllPost
import com.example.data.network.UploadApiService
import com.example.data.session.SessionManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.Serializable
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URLConnection

class NetworkRequests(val sessionManager: SessionManager,val client: HttpClient, val context: Context,val apiService: UploadApiService) {

    suspend fun uploadIdea(post: Post) {
        val response: HttpResponse = client.post("http://${ServerIp}:5000/api/post/upload") {
            contentType(ContentType.MultiPart.FormData) // Ensures correct multipart formatting
            setBody(
                MultiPartFormDataContent(
                    formData {
                        // Append thought text
                        append("thought", post.thought)

                        // Append file correctly
                        append(
                            key = "thoughtMedia",
                            value = post.thoughtMedia.readBytes(), // Convert file to ByteArray
                            headers = Headers.build {
                                append(HttpHeaders.ContentDisposition, "form-data; name=\"thoughtMedia\"; filename=\"${post.thoughtMedia.name}\"")
                                append(HttpHeaders.ContentType, "image/png") // Match correct MIME type
                            }
                        )
                    }
                )
            )
        }


        Log.v("response", response.body<String>())
        // Log request details
        Log.d("Request", "Sending thought: ${post.thought}")
        Log.d("Request", "File Name: ${post.thoughtMedia.name}")
        Log.d("Request", "File Size: ${post.thoughtMedia.length()} bytes")
        Log.d("Request", "File MIME Type: ${URLConnection.guessContentTypeFromName(post.thoughtMedia.name)}")
        Log.d("Response", "Response: ${response.status}, Body: ${response.body<String>()}")
    }


    suspend fun postIdea(post: Post): Unit {
        val thoughtBody = post.thought.toRequestBody("text/plain".toMediaTypeOrNull())

        val imagePart = post.thoughtMedia.let {
            val requestBody = it.asRequestBody("image/*".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("thoughtMedia", it.name, requestBody)
        }

        return apiService.uploadIdea(thoughtBody, imagePart)
    }

    suspend fun getAllPost():List<AllPost>{
    val response =  client.get("http://${ServerIp}:5000/api/post/all"){
        header(HttpHeaders.Authorization,sessionManager.getAuthToken())

        }

        Log.v("tag",response.status.value.toString())



        return response.body() as List<AllPost>

    }


}

@Serializable
data class Post(val thought: String)
