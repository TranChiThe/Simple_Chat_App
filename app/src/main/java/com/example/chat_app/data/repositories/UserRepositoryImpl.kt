package com.example.chat_app.data.repositories

import android.util.Log
import com.example.chat_app.domain.model.User
import com.example.chat_app.domain.model.User_
import com.example.chat_app.domain.repositories.UserRepository
import com.example.chat_app.domain.util.Argon2Helper
import com.example.chat_app.domain.util.BCryptHelper
import com.example.chat_app.domain.util.isValidEmail
import com.example.chat_app.domain.util.isValidPassword
import io.objectbox.Box
import io.objectbox.kotlin.equal
import io.objectbox.query.QueryBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userBox:Box<User>
):UserRepository {

    init {
        getAllUser()
    }

    override fun getAllUser(): Flow<List<User>> = callbackFlow {
        val query = userBox.query().build()
        val subscription = query.subscribe().observer{user ->
            trySend(user)
        }
        awaitClose{subscription.cancel()}
    }

    override suspend fun registerAccount(user: User) {
        withContext(Dispatchers.IO){
            if(isValidEmail(user.email) && isValidPassword(user.password)) {
                val hashPassword = BCryptHelper.hashPassword(user.password)
                val newUser = user.copy(password = hashPassword)
                userBox.put(newUser)
            }
        }
    }

    override suspend fun loginAccount(email: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            if (email.isBlank() || password.isBlank()) {
                return@withContext null
            }
            var user = userBox.query()
                .equal(User_.email, email, QueryBuilder.StringOrder.CASE_INSENSITIVE)
                .build()
                .findFirst()
            if(user != null && BCryptHelper.verifyPassword(password, user.password))
                return@withContext user
            null
        }
    }

}