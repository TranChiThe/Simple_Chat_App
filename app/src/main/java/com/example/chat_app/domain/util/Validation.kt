package com.example.chat_app.domain.util

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
    return email.matches(emailRegex)
}

fun isValidPassword(password: String): Boolean {
    return password.length >= 8 && password.any { it.isDigit() } && password.any { it.isLetter() }
}