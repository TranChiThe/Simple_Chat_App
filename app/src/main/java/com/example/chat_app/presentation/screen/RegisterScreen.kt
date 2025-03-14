package com.example.chat_app.presentation.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.chat_app.domain.util.isValidEmail
import com.example.chat_app.domain.util.isValidPassword
import com.example.chat_app.presentation.view_model.UserEvent
import com.example.chat_app.presentation.view_model.UserViewModel

@Composable
fun RegisterScreen(
    navController: NavHostController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    var userName = userViewModel.userName.value
    var email = userViewModel.email.value
    var password = userViewModel.password.value
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var showPassword by remember { mutableStateOf(false) }

    // Animation
    var isVisible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 800), label = ""
    )

    LaunchedEffect(Unit) {
        isVisible = true
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
                    text = "Create Account",
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
                    text = "Create an account to \n explore the app",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Username TextField
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userViewModel.onEvent(UserEvent.EnterUserName(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "user name",
                            tint = Color(0xFF0288D1)
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    placeholder = { Text("Username", color = Color.Gray) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF0288D1),
                        unfocusedBorderColor = Color(0xFFB3E5FC)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

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

                // Password TextField with Icon Toggle
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
                    },
                    shape = RoundedCornerShape(12.dp),
                    textStyle = TextStyle(fontSize = 16.sp),
                    placeholder = { Text("Password", color = Color.Gray) },
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF0288D1),
                        unfocusedBorderColor = Color(0xFFB3E5FC)
                    )
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

                // Register Button
                Button(
                    onClick = {
                        if (isValidEmail(email) && isValidPassword(password)) {
                            userViewModel.onEvent(UserEvent.RegisterAccount)
                            navController.navigate("login")
                        } else {
                            if (!isValidEmail(email)) emailError = "Invalid email!"
                            if (!isValidPassword(password)) passwordError = "Password must be >= 8 characters, including letters & numbers!"
                        }
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
                            text = "REGISTER",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Login Link
                TextButton(onClick = { navController.navigate("login") }) {
                    Text(
                        text = "Already have an account? Login",
                        color = Color(0xFF0288D1),
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}