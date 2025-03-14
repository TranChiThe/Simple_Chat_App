package com.example.chat_app.presentation.navigation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.chat_app.presentation.screen.WelcomeScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    // Trạng thái cho double back press
    var backPressedTime by remember { mutableStateOf(0L) }
    val doubleBackToExitThreshold = 2000L
    BackHandler(enabled = true) {
        val currentRoute = navController.currentBackStackEntry?.destination?.route
        // Chỉ áp dụng double back press cho các màn hình sau đăng nhập
        if (currentRoute != "welcome" && currentRoute != "login" && currentRoute != "register") {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime <= doubleBackToExitThreshold) {
                (context as? android.app.Activity)?.finish()
            } else {
                backPressedTime = currentTime
                Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
        } else {
            navController.popBackStack()
        }
    }
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navController = navController,
            startDestination = "welcome",
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
            composable(Screen.Welcome.route) { WelcomeScreen(navController = navController) }
            composable(Screen.Login.route) { LoginScreen(navController = navController) }
            composable(Screen.Register.route) { RegisterScreen(navController = navController) }
            composable(Screen.Search.route) { SearchScreen(navController = navController) }
            composable(Screen.Chat.route) { ChatScreen(navController = navController) }
            composable(Screen.Friend.route) { FriendScreen(navController = navController) }
            composable(Screen.Personal.route) { PersonalScreen(navController = navController) }
        }
    }
}




