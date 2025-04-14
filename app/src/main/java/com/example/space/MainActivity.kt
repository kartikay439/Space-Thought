package com.example.space

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.session.SessionManager
import com.example.space.screens.AppMainNavigation
import com.example.space.screens.bottomNavigation.BottomNavigationBarExample
import com.example.space.screens.topBar.TopBar

import com.example.space.ui.theme.SpaceTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        var isSplashScreenVisible = true


        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isSplashScreenVisible
            }
//            setOnExitAnimationListener{
//                val zoomX = Objec
//            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            SpaceTheme {
                val systemUiController = rememberSystemUiController()
                val systemNAvBarColour =Color.Transparent

                LaunchedEffect(Unit) {
                    systemUiController.setNavigationBarColor(
                            color = systemNAvBarColour
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
//                        .border(5.dp, Color.Black),
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    val sessionManager : SessionManager by inject()

                    Box(
                        Modifier.fillMaxSize().align(Alignment.Center).padding(bottom = 50.dp)
                    ){
                        AppMainNavigation(
                            navController,
                            sessionManager = sessionManager
                        )
                    }
                    Box(
                        Modifier.align(Alignment.BottomCenter)
                    ){
                        BottomNavigationBarExample(navController)

                    }
                    Box(Modifier.align(Alignment.TopCenter)){
                        TopBar()
                    }

                }
            }


        }
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            isSplashScreenVisible = false
        }


    }
}






