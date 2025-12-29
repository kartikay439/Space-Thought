package com.kartikay.space.screens.upload.component

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun UploadImagePicker(
    imageUri: Uri?,
    onPickClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clickable { onPickClick() },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp,Color.White),
        colors = CardDefaults.cardColors(Color.Gray)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.background(Color.White)) {

            if (imageUri == null) {
                Text(
                    text = "Tap to add image",
                    color = Color.Gray
                )
            } else {
                AsyncImage(
                    model = imageUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
