package com.kartikay.space.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kartikay.data.network.notification_socket.NotificationSockerService
import com.kartikay.space.screens.PostScreen
import com.kartikay.space.screens.UploadIdea
import com.kartikay.space.screens.UserScreen
import com.kartikay.space.screens.allPost.AllPostScreen
import com.kartikay.space.screens.bottomNavigation.BottomNavigationBarExample
import com.kartikay.space.screens.topBar.TopBar
import com.kartikay.space.screens.upload.UploadIdeaScreen
import com.kartikay.space.screens.userScreen.UserScreen

@Composable
fun Home() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomNavigationBarExample(navController)
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = PostScreen,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            composable<PostScreen>() {
                AllPostScreen()
            }

            composable<UploadIdea>() {
                UploadIdeaScreen(navController)
            }

            composable<UserScreen>() {
                UserScreen()
            }
        }
    }
}
