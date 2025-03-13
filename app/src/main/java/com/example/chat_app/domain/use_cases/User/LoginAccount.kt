package com.example.chat_app.domain.use_cases.User

import com.example.chat_app.domain.repositories.UserRepository

class LoginAccount (
    private val userRepository: UserRepository
){
    suspend operator fun invoke(email: String, password: String) = userRepository.loginAccount(email, password)
}