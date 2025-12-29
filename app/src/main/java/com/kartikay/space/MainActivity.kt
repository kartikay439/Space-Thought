package com.kartikay.space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.kartikay.space.screens.AppMainNavigation
import com.kartikay.space.ui.theme.SpaceTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kartikay.space.screens.notification_permission.NotificationViewModel
import org.koin.core.Koin
import org.koin.core.context.GlobalContext.get


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.remove()
        }



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SpaceTheme {
                val systemUiController = rememberSystemUiController()
                val systemNAvBarColour = Color.Transparent

                LaunchedEffect(Unit) {
                    systemUiController.setNavigationBarColor(
                        color = systemNAvBarColour
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)

                ) {
                    Box(
                        Modifier
                            .fillMaxSize()
                    ) {
                        AppMainNavigation(
                            navController
                        )
                    }


                }
            }


        }


    }
}






