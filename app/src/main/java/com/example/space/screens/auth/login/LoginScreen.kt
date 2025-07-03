package com.example.space.screens.home.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dotlottie.dlplayer.Mode
import com.example.business.ApiResponse
import com.example.business.model.LoginResponse
import com.example.business.model.User
import com.example.space.R
import com.example.space.screens.LoginScreen_UserScreen
import com.example.space.screens.SignUpScreen
import com.example.space.screens.auth.components.InputField
import com.example.space.screens.auth.components.SubmitButtonAuth
import com.example.space.screens.auth.login.LoginScreenViewModel
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    isLoginState:MutableState<Boolean>,
    loginScreenViewModel: LoginScreenViewModel = koinViewModel()
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val circularBarVisibility = remember { mutableStateOf(false) }
    val state = loginScreenViewModel.response_.collectAsState()
//    val isLogin = loginScreenViewModel.isLogin_.collectAsState()
    val bottomText = remember { mutableStateOf("") }
    val showAnimation = remember { mutableStateOf(true) }

    // Navigate after successful login
//    LaunchedEffect(isLogin.value) {
//        if (isLogin.value is ApiResponse.Success) {
//            navController.navigate(UserScreen) {
//                //come out login screen from stack
//                popUpTo("LoginScreen") { inclusive = true }
//            }
//        }
//    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "SPACE",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 72.sp,
                    letterSpacing = 10.sp,
                    fontWeight = FontWeight(800),
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.width(300.dp)
            )

//            if (isLogin.value is ApiResponse.Error) {
//                Text((isLogin.value as ApiResponse.Error).message, color = Color.Red)
//            }

            Text(
                "THOUGHT",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    letterSpacing = 10.sp,
                    fontWeight = FontWeight(800)
                ),
                textAlign = TextAlign.Right,
                modifier = Modifier.width(300.dp)
            )

            Box(
                modifier = Modifier
                    .size(350.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    InputField(email, "Email", false)
                    InputField(password, "Password", true)
                    SubmitButtonAuth("Login", rememberCoroutineScope()) {
                        circularBarVisibility.value = true
                        loginScreenViewModel.login(email.value, password.value)
                    }
                    Text(bottomText.value)
                }
            }

            Button(
                onClick = { navController.navigate(SignUpScreen) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color(0xF72196F3))
                        .width(200.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Sign")
                    Image(
                        painterResource(R.drawable.science),
                        contentDescription = "logo",
                        Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(100.dp))
                    )
                    Text("Up")
                }
            }

            // Handle API Response (Login Success/Error)
            when (state.value) {
                is ApiResponse.Success -> {
                    val loginResponse = (state.value as ApiResponse.Success<LoginResponse>).data
                    bottomText.value = loginResponse.token + "\n" + loginResponse.message
                    loginScreenViewModel.resetResponse()
                    isLoginState.value = true
                    navController.navigate(LoginScreen_UserScreen)
                }

                is ApiResponse.Error -> {
                    bottomText.value = (state.value as ApiResponse.Error).message
                }

                else -> {}
            }
        }

        if (showAnimation.value)
            DotLottieAnimation(
                source = DotLottieSource.Url("https://lottie.host/73b2650d-1f05-4dfe-af09-e23b1c0ea634/eRcn3criqr.lottie"),
                autoplay = true,
                loop = true,
                speed = 3f,
                useFrameInterpolation = false,
                playMode = Mode.FORWARD,
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.Center)
                    .size(450.dp)
            )

        LaunchedEffect(Unit) {
            delay(800)
            showAnimation.value = false
        }

        // Overlay Loader
        Surface(
            modifier = Modifier.size(if (circularBarVisibility.value) 1000.dp else 0.dp),
            color = Color(0xE6000000)
        ) { }

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(if (circularBarVisibility.value) 20.dp else 0.dp)
        )
    }
}