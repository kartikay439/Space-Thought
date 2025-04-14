package com.example.space.screens.home

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.space.screens.UploadIdea

@Composable
fun Home(token: String, navController: NavHostController) {
    Text(token)
    FloatingActionButton(
        onClick = {
            navController.navigate(UploadIdea)
        }
    ) {

    }
}