package com.example.space.screens.auth.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SubmitButtonAuth(text:String,coroutineScope:CoroutineScope,login:suspend ()->Unit){
    Button(
        onClick = { coroutineScope.launch { login() } }, // Use remembered coroutine scope
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            containerColor = Color.Black
        ),
        shape = RectangleShape,
        modifier = Modifier.width(100.dp)
    ) {
        Text(text, style = TextStyle(color = Color.White))
    }
}