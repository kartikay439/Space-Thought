package com.example.space.screens.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dotlottie.dlplayer.Mode
import com.example.space.screens.upload.font
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource

@Composable
fun TopBar(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))

            .background(Color.Transparent)

        // Your actual Box color
    ){
//            PatternSurface()

    Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)



//                .shadow(
//                    elevation = 15.dp, // Controls shadow depth
//                    shape = RoundedCornerShape(30.dp), // Smooth rounded shadow
//                    ambientColor = Color.Black.copy(alpha = 0.2f), // Soft black shadow
//                    spotColor = Color.Black.copy(alpha = 0.3f) // More depth
//                )
                .clip(RoundedCornerShape(15.dp))
                .background(brush = Brush.verticalGradient(listOf(Color(0xFFFFFFFF),Color(0xF72196F3)))) ,
           horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically

        ) {
            DotLottieAnimation(
                source = DotLottieSource.Url("https://lottie.host/f0adec24-c295-4fcf-b688-be75c4c8addb/VFXnssJscy.lottie"),
                autoplay = true,
                loop = true,
                speed = 3f,
                useFrameInterpolation = false,
                playMode = Mode.FORWARD,
                modifier = Modifier
                    .background(Color.Transparent)
                              .size(45.dp)
            )
            Text(
                "SHARE THOUGHT",
                style = TextStyle(
                    fontWeight = FontWeight(800),

                    fontSize = 16.sp,
                    fontFamily = font,
                    color = Color.Black,
                    letterSpacing = 9.sp
                ),
                modifier = Modifier.height(25.dp).background(Color.Transparent)
            )
        }
    }
}