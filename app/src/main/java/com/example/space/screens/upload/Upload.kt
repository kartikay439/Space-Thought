package com.example.space.screens.upload

import android.content.Context

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.data.session.SessionManager
import com.example.space.R
import com.example.space.screens.topBar.TopBar
import kotlinx.coroutines.*
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

const val SERVER_URL = "http://192.168.8.221:5000/api/post/upload"

val font = FontFamily(Font(R.font.rocknrollone))

@Composable
fun UploadIdea(sessionManager: SessionManager, uploadViewModel: UploadViewModel = koinViewModel()) {
    val context = LocalContext.current
    var selectedImages by remember { mutableStateOf<Uri?>(null) }
    var thoughts by remember { mutableStateOf("") }
    var isUploading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val token = remember { mutableStateOf(sessionManager.getAuthToken()) }




    val pickMultiMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        selectedImages = it
    }

    // Convert URI to File
    fun uriToFile(context: Context, uri: Uri): File {
        val fileName = "thoughtMedia_${System.currentTimeMillis()}.jpg" // Unique filename
        val file = File(context.cacheDir, fileName)
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        FileOutputStream(file).use { outputStream ->
            inputStream?.copyTo(outputStream)
        }
        return file
    }

    Column(
        modifier = Modifier
//            .background(Color(0xFF2E2538))
            .height(650.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .height(400.dp)
                .shadow(15.dp, RoundedCornerShape(12.dp), ambientColor = Color(0xFFC1D8C3)),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {

                //use flow here
//                Text(token.value?:"no values")
                // Display selected images
//                LazyRow(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    items(selectedImages) { imageUrl ->

                Image(
                    painter = rememberAsyncImagePainter(selectedImages),
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .size(150.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(12.dp))


                )
            }
//                }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(5.dp)

            ) {

                // Pick Image Button
                Button(
                    onClick = { pickMultiMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(containerColor =Color(0x2A2196F3))
                ) {
                    Text(
                        "Pick Image",style = TextStyle(
                            fontFamily = font,
                            color = Color.Black,
                            fontSize = 14.sp,

                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Thought Input Field
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = thoughts,
                    onValueChange = { thoughts = it },
                    label = { Text("What's on your mind?") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Upload Button
                Button(
                    onClick = {
                        if (selectedImages != null) {
                            val file = uriToFile(context, selectedImages!!)

                            Log.d(
                                "Upload",
                                "File Path: ${file.absolutePath}, Exists: ${file.exists()}, Size: ${file.length()} bytes"
                            )

                            if (!file.exists() || file.length() == 0L) {
                                Toast.makeText(
                                    context,
                                    "⚠️ File creation failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@Button
                            }

                            coroutineScope.launch {
                                isUploading = true
                                try {
                                    uploadViewModel.uploadIdea(thoughts, file)
                                    Toast.makeText(
                                        context,
                                        "✅ Upload Successful!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "❌ Upload Failed: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } finally {
                                    isUploading = false
                                }
                            }
                        } else {
                            Toast.makeText(context, "⚠️ Please select an image", Toast.LENGTH_SHORT)
                                .show()
                        }
                    },
                    modifier = Modifier.width(150.dp),
                    enabled = !isUploading,
                    colors = ButtonDefaults.buttonColors(containerColor =Color(0x2A2196F3))
                ) {
                    if (isUploading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White
                        )
                    } else {
                        Text(
                            "Upload",
                            style = TextStyle(
                                fontFamily = font,
                                color = Color.Black,
                                fontSize = 14.sp,
                            ),

                            )
                    }
                }
            }
        }
    }
}

@Composable
fun PatternSurface() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp),
        color = Color.Transparent, // Background color
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val dotSpacing = 25.dp.toPx()
            val dotRadius = 5.dp.toPx()

            for (x in 0..size.width.toInt() step dotSpacing.toInt()) {
                for (y in 0..size.height.toInt() step dotSpacing.toInt()) {
                    drawCircle(
                        color = Color(0xFF6200EA),
                        radius = dotRadius,
                        center = androidx.compose.ui.geometry.Offset(x.toFloat(), y.toFloat())
                    )
                }
            }
        }
    }
}