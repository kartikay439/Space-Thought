package com.example.space.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.data.session.SessionManager
import com.example.space.screens.allPost.AllPostScreen
import com.example.space.screens.auth.signup.SignUpScreen
import com.example.space.screens.home.Home
import com.example.space.screens.home.login.LoginScreen
import com.example.space.screens.upload.UploadIdea
import io.ktor.client.HttpClient
import kotlinx.serialization.Serializable

@Composable
fun AppMainNavigation(navController: NavHostController,sessionManager: SessionManager) {

    NavHost(
        navController = navController,
        startDestination = LoginScreen
    ) {

        composable<LoginScreen> {
            LoginScreen(sessionManager,navController)
        }
        composable<HomeScreen> {
            val args = it.toRoute<HomeScreen>()
            Home(token = args.token,navController)
        }
        composable<UploadIdea> {
            UploadIdea(sessionManager)
        }
        composable<PostScreen> {
            AllPostScreen()
        }
        composable<SignUpScreen> {
            SignUpScreen(
                sessionManager,
                navController
            )
        }

    }
}

@Serializable
object LoginScreen

@Serializable
object SignUpScreen

@Serializable
object PostScreen

@Serializable
object UploadIdea

@Serializable
data class HomeScreen(val token: String)
