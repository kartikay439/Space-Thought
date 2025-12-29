package com.kartikay.space.screens.splashScreen

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kartikay.business.ApiResponse
import com.kartikay.data.network.notification_socket.NotificationSockerService
import com.kartikay.space.R // Import your R file
import com.kartikay.space.screens.AppNavigationViewModel
import com.kartikay.space.screens.HomeScreen
import com.kartikay.space.screens.LoginScreen
import com.kartikay.space.screens.notification_permission.NotificationHelper
import com.kartikay.space.screens.notification_permission.NotificationPermission
import com.kartikay.space.screens.notification_permission.NotificationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LottieSplashScreen(
    navController: NavHostController,
    mainNavigationViewModel: AppNavigationViewModel = koinViewModel(),
    notificationViewModel: NotificationViewModel = koinViewModel(),
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.happy_spacemen))
    val progress by animateLottieCompositionAsState(composition)
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        notificationViewModel.startListning(context)
    }

    // Navigate only when animation finishes
    LaunchedEffect(progress) {
        if (progress == 1f) {
            when (mainNavigationViewModel.response.value) {

                is ApiResponse.Success -> {
                    if (Build.VERSION.SDK_INT >= 33) {
                        val permission = Manifest.permission.POST_NOTIFICATIONS
                        if (ContextCompat.checkSelfPermission(
                                context,
                                permission
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            ActivityCompat.requestPermissions(
                                context as Activity,
                                arrayOf(permission),
                                1001
                            )
                        }
                    }

                    navController.navigate(HomeScreen)
                }

                is ApiResponse.Loading -> {
//                    CircularProgressIndicator()
//                    Text("wait...")
                }

                is ApiResponse.Error -> {
                    navController.navigate(LoginScreen)
                }

                else -> {}
            }
//            navController.navigate() {
//                popUpTo("splash") { inclusive = true }
//            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Must match windowSplashScreenBackground
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.fillMaxSize() // Full screen animation
        )
    }
}