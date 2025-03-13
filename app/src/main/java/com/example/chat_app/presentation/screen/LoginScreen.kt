package com.example.chat_app.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen (navController: NavHostController) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Card (
            modifier = Modifier
                .wrapContentSize()
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
                    value = "", onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                    },
                    placeholder = { Text("Email") }
                )
                Spacer(modifier = Modifier.padding(5.dp))
                OutlinedTextField(
                    value = "", onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "password")
                    },
                    placeholder = { Text("Password") }
                )
                Spacer(modifier = Modifier.padding(16.dp))

                Button(
                    onClick = {navController.navigate("search")},
                    modifier = Modifier.fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF))
                ) {
                    Text(text = "LOGIN")
                }
                Spacer(modifier = Modifier.padding(8.dp))
                TextButton(
                    onClick = { navController.navigate("register") }
                ) {
                    Text(text = "Don't have an account? Register", color = Color.Blue)
                }
            }
        }
    }
}