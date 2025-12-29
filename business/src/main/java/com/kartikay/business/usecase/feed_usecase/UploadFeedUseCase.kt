import android.util.Log
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.post.UploadResponse
import com.kartikay.business.repository.FeedRepository
import java.io.File

class UploadFeedUseCase(
    private val repository: FeedRepository
) {

    suspend operator fun invoke(
        thought: String,
        image: File
    ): ApiResponse<UploadResponse> {
        Log.v("Upload", "Inside Uplpoad")
        return repository.uploadFeed(thought, image)
    }
}
