package com.example.space.screens.userScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dotlottie.dlplayer.Mode
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserScreen(userScreenViewModel: UserScreenViewModel = koinViewModel()) {
    val user = userScreenViewModel.user.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        DotLottieAnimation(
            source = DotLottieSource.Url("https://lottie.host/54322354-d330-4456-aeeb-28308c3941a8/RfgzU9rT95.lottie"),
            autoplay = true,
            loop = true,
            speed = 3f,
            useFrameInterpolation = false,
            playMode = Mode.FORWARD,
            modifier = Modifier.background(Color.Transparent).size(500.dp)

        )
        Text(
            user.value.name,
            style = TextStyle(
                fontSize = 55.sp,
            ),
            modifier = Modifier
                .padding(5.dp)
//                .border(1.dp, color = Color.Cyan)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            user.value.email,
            style = TextStyle(
                fontSize = 24.sp,
            ),
            modifier = Modifier
                .padding(5.dp)
//                .border(1.dp, color = Color.Cyan)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}