package com.example.chat_app.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chat_app.domain.util.isValidEmail
import com.example.chat_app.domain.util.isValidPassword
import com.example.chat_app.presentation.view_model.UserEvent
import com.example.chat_app.presentation.view_model.UserEvent.RegisterAccount
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


    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(40.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userViewModel.onEvent(UserEvent.EnterUserName(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "user name")
                    },
                    placeholder = { Text("User name") },
                    )
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { userViewModel.onEvent(UserEvent.EnterEmail(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                    },
                    placeholder = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    )
                emailError?.let {
                    Text(text = it, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }
                LaunchedEffect(emailError) {
                    kotlinx.coroutines.delay(1000)
                    emailError = null
                }

                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { userViewModel.onEvent(UserEvent.EnterPassword(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
                    },
                    placeholder = { Text("Password") },
                    visualTransformation = if(showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    )
                passwordError?.let {
                    Text(text = it, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
                }

                LaunchedEffect(passwordError) {
                    kotlinx.coroutines.delay(1500)
                    passwordError = null
                }
                Spacer(modifier = Modifier.height(16.dp))
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
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF))
                ) {
                    Text(text = "REGISTER")
                }

                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = { navController.navigate("login") }
                ) {
                    Text(text = "You already have an account? Login", color = Color.Blue)
                }
            }
        }
    }
}
