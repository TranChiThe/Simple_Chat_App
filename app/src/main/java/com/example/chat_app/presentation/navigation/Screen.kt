package com.example.chat_app.presentation.navigation

sealed class Screen(val route: String) {
    object Welcome: Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Search : Screen("search")
    object Chat : Screen("chat")
    object Friend : Screen("friend")
    object Personal : Screen("personal")
}