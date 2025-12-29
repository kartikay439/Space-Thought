package com.kartikay.space.screens.upload

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kartikay.business.ApiResponse
import com.kartikay.space.ui.theme.blueMain
import org.koin.androidx.compose.koinViewModel
import java.io.File

// --- Constants for our "Blueish" Theme ---
private val DeepSpaceBlue = Color(0xFF0F172A)
private val GalaxyBlue = Color(0xFF1E293B)
private val NeonCyan = Color(0xFF06B6D4)
private val ElectricBlue = Color(0xFF3B82F6)
private val StarlightWhite = Color(0xFFF1F5F9)

@Composable
fun UploadIdeaScreen(
    navController: NavController,
    uploadViewModel: UploadViewModel = koinViewModel()
) {
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    var thought by remember { mutableStateOf("") }

    // Animation state for entry
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { isVisible = true }

    val context = LocalContext.current
    val uploadState by uploadViewModel.uploadState.collectAsState()

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> selectedImage = uri }

    // Side Effects
    LaunchedEffect(uploadState) {
        when (uploadState) {
            is ApiResponse.Success -> {
                Toast.makeText(context, "Idea transmitted to orbit! 🚀", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }

            is ApiResponse.Error -> {
                Toast.makeText(
                    context,
                    (uploadState as ApiResponse.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }


    // Main Container with Gradient Background
    Box(
        modifier = Modifier
            .background(Color(0xFF23345E))
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(DeepSpaceBlue, GalaxyBlue, Color.Black)
//                )
//            )
    ) {
        // Decorative background glow blobs
        Box(
            modifier = Modifier
                .offset(x = (-100).dp, y = (-100).dp)
                .size(300.dp)
                .blur(80.dp)
                .background(blueMain.copy(alpha = 0.7f), CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = 100.dp, y = 100.dp)
                .size(300.dp)
                .blur(80.dp)
                .background(blueMain, CircleShape)
        )

        Scaffold(
            containerColor = Color.Transparent,
            topBar = { GlassyTopBar(navController) }
        ) { padding ->

            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                // Animated Header
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + slideInVertically { -40 }
                ) {
                    Text(
                        text = "Create\nNew Vision",
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            brush = Brush.linearGradient(
                                colors = listOf(StarlightWhite, NeonCyan)
                            )
                        ),
                        lineHeight = 48.sp
                    )
                }

                // Modular Components
                AnimatedVisibility(
                    visible = isVisible,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { 40 })
                ) {
                    HolographicImagePicker(
                        selectedImage = selectedImage,
                        onPickImage = {
                            imagePicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        onRemoveImage = { selectedImage = null }
                    )
                }

                NeonInputArea(
                    value = thought,
                    onValueChange = { thought = it }
                )

//                Spacer(modifier = Modifier.weight(1f))

                CosmicUploadButton(
                    enabled = selectedImage != null && thought.isNotBlank(),
                    isLoading = uploadState is ApiResponse.Loading,
                    onClick = {
                        val file = uriToFile(context, selectedImage!!)
                        uploadViewModel.uploadFeed(thought, file)
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// -------------------------------------------------------------------------
// 🧩 MODULAR UI COMPONENTS
// -------------------------------------------------------------------------

@Composable
private fun GlassyTopBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(40.dp)
                .background(Color.White.copy(alpha = 0.1f), CircleShape)
                .border(1.dp, Color.White.copy(alpha = 0.2f), CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = StarlightWhite
            )
        }
    }
}

@Composable
private fun HolographicImagePicker(
    selectedImage: Uri?,
    onPickImage: () -> Unit,
    onRemoveImage: () -> Unit
) {
    val scale by animateFloatAsState(if (selectedImage != null) 1f else 0.98f, label = "scale")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .scale(scale)
//            .clip(RoundedCornerShape(24.dp))
            .background(Color.Black.copy(alpha = 0.5f))
//            .border(
//                BorderStroke(
//                    1.dp,
//                    Brush.linearGradient(listOf(Color.White.copy(alpha = 0.7f), Color.Transparent))
//                ),
//                RoundedCornerShape(24.dp)
//            )
            .clickable(enabled = selectedImage == null) { onPickImage() }
    ) {
        if (selectedImage != null) {
            Image(
                painter = rememberAsyncImagePainter(selectedImage),
                contentDescription = "Selected",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Glassy overlay at bottom of image
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Image Selected", color = Color.White.copy(alpha = 0.8f), fontSize = 12.sp)
                    IconButton(
                        onClick = onRemoveImage,
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.White.copy(alpha = 0.2f), CircleShape)
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "Remove",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .shadow(20.dp, CircleShape, spotColor = NeonCyan)
                        .background(
                            Brush.linearGradient(listOf(ElectricBlue, NeonCyan)),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Upload",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Tap to visualize",
                    color = NeonCyan,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium)
                )
                Text(
                    text = "Supports JPG, PNG",
                    color = Color.White.copy(alpha = 0.4f),
                    style = TextStyle(fontSize = 12.sp)
                )
            }
        }
    }
}

@Composable
private fun NeonInputArea(
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "DESCRIPTION",
            style = TextStyle(
                color = ElectricBlue,
                fontSize = 11.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(start = 8.dp)
        )

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = StarlightWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            cursorBrush = SolidColor(NeonCyan),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Black.copy(.5f))
//                        .border(1.dp, Color.White.copy(alpha = 0.1f))
                        .padding(20.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Describe your innovation...",
                            color = Color.White.copy(alpha = 0.3f),
                            fontSize = 18.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
private fun CosmicUploadButton(
    enabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val finalEnabled = enabled && !isLoading
    val buttonAlpha by animateFloatAsState(
        if (finalEnabled) 1f else 0.5f,
        label = "alpha"
    )

    Button(
        onClick = onClick,
        enabled = finalEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .shadow(
                elevation = if (finalEnabled) 20.dp else 0.dp,
                spotColor = ElectricBlue
            )
            .padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = if (finalEnabled) {
                        Brush.horizontalGradient(listOf(ElectricBlue, NeonCyan))
                    } else {
                        SolidColor(Color.Gray.copy(alpha = 0.3f))
                    }
                )
                .then(Modifier.alpha(buttonAlpha)),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "LAUNCH IDEA",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

// Modifier helper for clean code
fun Modifier.alpha(alpha: Float) = this.then(Modifier.drawBehind {
    drawRect(Color.Transparent, blendMode = androidx.compose.ui.graphics.BlendMode.SrcAtop)
    drawRect(
        Color.Black.copy(alpha = 1f - alpha),
        blendMode = androidx.compose.ui.graphics.BlendMode.DstIn
    )
})

// -------------------------------------------------------------------------
// 🛠 UTILITIES
// -------------------------------------------------------------------------

fun uriToFile(context: Context, uri: Uri): File {
    val file = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
    try {
        context.contentResolver.openInputStream(uri)?.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return file
}