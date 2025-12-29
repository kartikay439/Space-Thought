package com.kartikay.space.screens.userScreen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dotlottie.dlplayer.Mode
import com.kartikay.business.ApiResponse
import com.kartikay.business.model.user.User
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserScreen(
    viewModel: UserScreenViewModel = koinViewModel()
) {
    val userState by viewModel.user.collectAsState()

    // background gradient
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1A237E), // Deep Indigo
            Color(0xFF3949AB),
            Color(0xFF8C9EFF)  // Light Indigo
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        // Smoothly animate between states
        AnimatedContent(
            targetState = userState,
            transitionSpec = {
                fadeIn(animationSpec = tween(600)) + scaleIn(initialScale = 0.9f) togetherWith
                        fadeOut(animationSpec = tween(400))
            },
            label = "ScreenStateTransition"
        ) { state ->
            when (state) {
                is ApiResponse.Loading -> {
                    LoadingView()
                }
                is ApiResponse.Success -> {
                    SuccessView(user = state.data)
                }
                is ApiResponse.Error -> {
                    ErrorView(
                        message = state.message,
                        onRetry = { /* trigger refresh logic here */ }
                    )
                }
                else -> {

                }
            }
        }
    }
}

// ---------------------------------------------------------
// 🔄 Loading View
// ---------------------------------------------------------
@Composable
fun LoadingView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DotLottieAnimation(
            source = DotLottieSource.Url("https://lottie.host/54322354-d330-4456-aeeb-28308c3941a8/RfgzU9rT95.lottie"),
            autoplay = true,
            loop = true,
            speed = 2.5f,
            playMode = Mode.FORWARD,
            modifier = Modifier.size(280.dp)
        )
        Text(
            text = "Fetching User Data...",
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

// ---------------------------------------------------------
// ✅ Success View with Entrance Animations
// ---------------------------------------------------------
@Composable
fun SuccessView(user: User) { // Assuming User is your data class
    // Pulse Animation for Avatar
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
        ) {
            // Profile Picture with Pulse
            Box(
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .scale(scale)
                        .background(
                            brush = Brush.linearGradient(
                                listOf(Color(0xFF6200EA), Color(0xFF00B0FF))
                            ),
                            shape = CircleShape
                        )
                        .blur(10.dp) // Optional: Requires modifier chaining or custom blur
                )

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .border(4.dp, Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Avatar",
                        tint = Color.Gray,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Animated Text Entry
            Text(
                text = user.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A237E),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user.email,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Decorative Badge
            Surface(
                color = Color(0xFFE8EAF6),
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                    text = "VERIFIED MEMBER",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF3949AB),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ---------------------------------------------------------
// ❌ Error View
// ---------------------------------------------------------
@Composable
fun ErrorView(message: String?, onRetry: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(24.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Warning, // Or use a Sad Face icon
            contentDescription = "Error",
            tint = Color(0xFFFF5252),
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Oops!",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = message ?: "Something went wrong loading the user data.",
            color = Color.White.copy(alpha = 0.9f),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5252)),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Try Again")
        }
    }
}