package com.example.chat_app.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chat_app.presentation.screen.ChatScreen
import com.example.chat_app.presentation.screen.FriendScreen
import com.example.chat_app.presentation.screen.LoginScreen
import com.example.chat_app.presentation.screen.PersonalScreen
import com.example.chat_app.presentation.screen.RegisterScreen
import com.example.chat_app.presentation.screen.SearchScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = "login",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth }, // Slide từ phải sang trái
                    animationSpec = tween(durationMillis = 300)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth }, // Slide ra bên trái
                    animationSpec = tween(durationMillis = 300)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth }, // Slide từ trái sang phải khi quay lại
                    animationSpec = tween(durationMillis = 300)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth }, // Slide ra bên phải khi quay lại
                    animationSpec = tween(durationMillis = 300)
                )
            }
        ) {
            composable(Screen.Login.route) { LoginScreen(navController = navController) }
            composable(Screen.Register.route) { RegisterScreen(navController = navController) }
            composable(Screen.Search.route) { SearchScreen(navController = navController) }
            composable(Screen.Chat.route) { ChatScreen(navController = navController) }
            composable(Screen.Friend.route) { FriendScreen(navController = navController) }
            composable(Screen.Personal.route) { PersonalScreen(navController = navController) }

        }
    }
}




