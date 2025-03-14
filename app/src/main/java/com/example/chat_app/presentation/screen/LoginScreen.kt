package com.example.chat_app.presentation.screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chat_app.R
import com.example.chat_app.presentation.view_model.UserEvent
import com.example.chat_app.presentation.view_model.UserViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var email = userViewModel.email.value
    var password = userViewModel.password.value
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var showPassword by remember { mutableStateOf(false) }
    var loginState = userViewModel.loginState

    // Animation
    var isVisible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 800), label = ""
    )

    LaunchedEffect(Unit) {
        isVisible = true
    }
    LaunchedEffect(loginState) {
        if (loginState == "Success") {
            navController.navigate("search")
        }
    }
    LaunchedEffect(loginState) {
        if (loginState == "Success") {
            navController.navigate("chat") {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    BackHandler(enabled = loginState == "Success") {
    }
    // Gradient Background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFB3E5FC), Color.White)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .wrapContentSize()
                .padding(24.dp)
                .shadow(12.dp, RoundedCornerShape(24.dp))
                .alpha(alpha),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .width(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Title with Gradient
                Text(
                    text = "Login Here",
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.ExtraBold,
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF0288D1), Color(0xFF00ACC1))
                        )
                    ),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Welcome back, you've been missed!",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Login State Message
                loginState?.let {
                    Text(
                        text = it,
                        color = if (it.contains("Success")) Color(0xFF00C853) else Color.Red,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = { userViewModel.onEvent(UserEvent.EnterEmail(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "email",
                            tint = Color(0xFF0288D1)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    placeholder = { Text("Email", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF0288D1),
                        unfocusedBorderColor = Color(0xFFB3E5FC)
                    )
                )
                emailError?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                LaunchedEffect(emailError) {
                    kotlinx.coroutines.delay(1000)
                    emailError = null
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = { userViewModel.onEvent(UserEvent.EnterPassword(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "password",
                            tint = Color(0xFF0288D1)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    placeholder = { Text("Password", color = Color.Gray) },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF0288D1),
                        unfocusedBorderColor = Color(0xFFB3E5FC)
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = {showPassword = !showPassword},
                            modifier = Modifier.size(24.dp),
                            colors = IconButtonDefaults.iconButtonColors(Color.White)
                        ) {
                            Icon(
                                painter = if (showPassword) painterResource(R.drawable.eye_icon_icons_com_71204)
                                else painterResource(R.drawable.eye_slash_svgrepo_com),
                                contentDescription = "show password"
                            )
                        }
                    }
                )
                passwordError?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                LaunchedEffect(passwordError) {
                    kotlinx.coroutines.delay(1500)
                    passwordError = null
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Login Button
                Button(
                    onClick = {
                        if (email.isBlank()) {
                            emailError = "Please enter email!"
                            return@Button
                        }
                        if (password.isBlank()) {
                            passwordError = "Please enter password!"
                            return@Button
                        }
                        userViewModel.onEvent(
                            UserEvent.LoginAccount(
                                email = email,
                                password = password
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .shadow(8.dp, RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(Color(0xFF0288D1), Color(0xFF00ACC1))
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "LOGIN",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Register Link
                TextButton(onClick = { navController.navigate("register") }) {
                    Text(
                        text = "Don't have an account? Register",
                        color = Color(0xFF0288D1),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}