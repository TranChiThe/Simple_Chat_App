package com.example.chat_app.domain.use_cases.User

import com.example.chat_app.domain.model.User
import com.example.chat_app.domain.repositories.UserRepository

class RegisterAccount(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) = userRepository.registerAccount(user)
}