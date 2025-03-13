package com.example.chat_app.domain.repositories

import com.example.chat_app.domain.model.User
import com.example.chat_app.presentation.navigation.Screen
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUser():Flow<List<User>>
    suspend fun registerAccount (user: User)
    suspend fun loginAccount(email: String, password: String): User?
}