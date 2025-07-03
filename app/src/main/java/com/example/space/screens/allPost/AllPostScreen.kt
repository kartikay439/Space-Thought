package com.example.space.screens.allPost

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.business.model.AllPost
import com.example.space.screens.topBar.TopBar
import com.example.space.screens.upload.font
import org.koin.androidx.compose.koinViewModel


@Composable
fun AllPostScreen(allPostScreenViewModel: AllPostScreenViewModel = koinViewModel()) {
    val state = allPostScreenViewModel.state.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()

    ) {

//        Spacer(Modifier.height(5.dp))
        when (state.value?.post) {
            null -> CircularProgressIndicator()
            else -> LazyColumn {
                items(state.value?.post ?: emptyList()) { value ->
                    item(value)
                }

                item {
                    allPostScreenViewModel.getAllPost()
                }
            }
        }

    }


}

@Composable
fun item(value: AllPost) {
    Surface(
        Modifier
            .fillMaxWidth()
            .height(600.dp)
//                            .padding(bottom = 25.dp),
        ,
        color = Color(0x14C4B9D2)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box() {
                Image(
                    rememberAsyncImagePainter(value.thoughtMedia.get(0)),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(500.dp),
                    contentScale = ContentScale.Crop
                )
                Text(
                    value.username,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight(100)
                    ),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .width(200.dp)
                        .background(Color.White)
                        .padding(10.dp)
                        .shadow(15.dp, RoundedCornerShape(2.dp), true),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                value.thought,
                modifier = Modifier
                    .background(Color(0x2A2196F3))
                    .fillMaxWidth()
                    .height(50.dp),
                textAlign = TextAlign.Center
            )

        }
    }
}