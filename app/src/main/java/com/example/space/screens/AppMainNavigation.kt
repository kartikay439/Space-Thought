package com.example.space.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.business.ApiResponse
import com.example.data.session.SessionManager
import com.example.space.screens.allPost.AllPostScreen
import com.example.space.screens.auth.signup.SignUpScreen
import com.example.space.screens.bottomNavigation.BottomNavigationBarExample
import com.example.space.screens.home.Home
import com.example.space.screens.home.login.LoginScreen
import com.example.space.screens.topBar.TopBar
import com.example.space.screens.upload.UploadIdea
import com.example.space.screens.userScreen.UserScreen
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppMainNavigation(
    navController: NavHostController,
    sessionManager: SessionManager,
    appNavigationViewModel: AppNavigationViewModel = koinViewModel()
) {
    val isLoginState = remember { mutableStateOf(false) }
    val showNavHost = remember { mutableStateOf(false) }

    val loginStatus = appNavigationViewModel.isLogin.collectAsState()

    LaunchedEffect(loginStatus.value) {
        delay(5000) // Shorter delay for better UX
        if (loginStatus.value is ApiResponse.Success) {
            isLoginState.value = true
        }
        showNavHost.value = true
    }

    if (showNavHost.value) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
//                        .border(5.dp, Color.Black),
            ,
            contentAlignment = Alignment.Center
        ) {

            Box(
                Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .padding(bottom = 50.dp)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = LoginScreen_UserScreen
                ) {
                    composable<LoginScreen_UserScreen> {
                        if (isLoginState.value) {
                            UserScreen()
                        } else {
                            LoginScreen(navController, isLoginState)
                        }
                    }

                    composable<HomeScreen> {
                        val args = it.toRoute<HomeScreen>()
                        Home(token = args.token, navController)
                    }

                    composable<UploadIdea> {
                        UploadIdea(sessionManager, navController)
                    }

                    composable<PostScreen> {
                        AllPostScreen()
                    }

                    composable<SignUpScreen> {
                        SignUpScreen(isLoginState,navController)
                    }
                }
            }
            if (isLoginState.value) {
                Box(
                    Modifier.align(Alignment.BottomCenter)
                ) {
                    BottomNavigationBarExample(navController)

                }
                Box(Modifier.align(Alignment.TopCenter)) {
                    TopBar()
                }
            }

        }


    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator(modifier = Modifier.size(100.dp))

        }
    }
}


@Serializable
object SignUpScreen

@Serializable
object PostScreen

@Serializable
object UploadIdea

@Serializable
data class HomeScreen(val token: String)

@Serializable
object LoginScreen_UserScreen
