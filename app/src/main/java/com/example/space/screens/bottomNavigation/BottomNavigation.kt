package com.example.space.screens.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.business.usecase.GetAllPostUseCase
import com.example.space.screens.HomeScreen
import com.example.space.screens.LoginScreen
import com.example.space.screens.PostScreen
import com.example.space.screens.UploadIdea
import com.example.space.screens.allPost.AllPostScreen
import com.example.space.screens.allPost.AllPostScreenViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject


@Composable
fun BottomNavigationBarExample(navController: NavController) {
    var selectedItem = remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize().background(Color(0x112196F3)),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // Increased height to fit buttons
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(brush = Brush.verticalGradient(listOf(Color(0xFFFFFFFF),Color(0xFFD1CED3))))
                    .padding(
                        bottom = WindowInsets.navigationBars.asPaddingValues()
                            .calculateBottomPadding()
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                NavButton(navController, "NEW")
                NavButton(navController, "IDEAS")
                NavButton(navController, "ME")
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun NavButton(navController: NavController?, text: String) {
    val x = remember { mutableStateOf(0.dp) }
    val coroutineScope = rememberCoroutineScope() // Use Composable scope instead of GlobalScope

    Column(
        modifier = Modifier
            .width(80.dp + x.value)
            .height(40.dp + x.value)
            .background(Color(0x2A2196F3), RoundedCornerShape(10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() }, // Remove ripple
                indication = null // Disable default animation
            ) {


                coroutineScope.launch { // Safe scope for animations
                    x.value = 10.dp
                    delay(100)
                    x.value = 0.dp
                    when (text) {
                        "NEW" -> navController?.navigate(UploadIdea)
                        "IDEAS" -> navController?.navigate(PostScreen)
                        "ME" -> navController?.navigate(LoginScreen)
                    }
                }
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
    }
}
