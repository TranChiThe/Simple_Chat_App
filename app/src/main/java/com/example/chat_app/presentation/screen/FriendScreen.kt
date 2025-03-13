package com.example.chat_app.presentation.screen

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.chat_app.R

@Composable
fun FriendScreen (navController: NavHostController) {
    Icon(painter = painterResource(id= R.drawable.friend_svgrepo_com), contentDescription = null)
}