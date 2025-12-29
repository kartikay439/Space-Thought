package com.kartikay.space.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kartikay.space.screens.auth.signup.SignUpScreen
import com.kartikay.space.screens.home.Home
import com.kartikay.space.screens.home.login.LoginScreen
import com.kartikay.space.screens.splashScreen.LottieSplashScreen
import kotlinx.serialization.Serializable

@Composable
fun AppMainNavigation(
    navController: NavHostController
) {
    AppNavigation(navController)
}

@Composable
fun AppNavigation(
    navController: NavHostController,

    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        NavHost(
            navController = navController,
            startDestination = SplashScreen
        ) {
            composable<HomeScreen> {
                Home()
            }

            composable<SplashScreen> {
                LottieSplashScreen(navController)
            }
            composable<LoginScreen> {
                LoginScreen(navController)
            }
            composable<SignUpScreen> {
                SignUpScreen(navController)
            }

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
object HomeScreen

@Serializable
object LoginScreen

@Serializable
object UserScreen

@Serializable
object SplashScreen

