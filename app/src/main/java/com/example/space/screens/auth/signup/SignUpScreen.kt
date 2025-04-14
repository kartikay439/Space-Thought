package com.example.space.screens.auth.signup


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.data.session.SessionManager
import com.example.space.R
import com.example.space.screens.auth.components.InputField
import com.example.space.screens.auth.components.SubmitButtonAuth
import com.example.space.screens.auth.login.LoginScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    sessionManager: SessionManager,
    navController: NavHostController,
    loginScreenViewModel: LoginScreenViewModel = koinViewModel()
) {
    val email = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val state = loginScreenViewModel.response_.collectAsState()

    val coroutineScope = rememberCoroutineScope() // Use this to launch coroutines
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .height(600.dp)
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "SPACE",
            style = TextStyle(
                color = Color.Black,
                fontSize = 72.sp,
                letterSpacing = 10.sp,
                fontWeight = FontWeight(800)
            )
        )
        Box(
            modifier = Modifier
                .size(350.dp)
//                .border(0.dp, Color.Black)
                .background(Color.Transparent),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                InputField(name, "Name", false)
                InputField(email, "Email", false)
                InputField(password, "Password", true)
                SubmitButtonAuth("SignUp", coroutineScope) {
                    loginScreenViewModel.login(email.value, password.value)
//                    sessionManager.addAuthToken(state.value.token)
                }
                Text(state.value.toString())
            }

        }
        Image(
            painterResource(R.drawable.science),
            contentDescription = "logo",
            modifier = Modifier.size(50.dp)
        )

    }
}
