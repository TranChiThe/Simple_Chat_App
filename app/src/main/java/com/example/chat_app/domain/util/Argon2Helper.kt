package com.example.chat_app.domain.util

import de.mkammerer.argon2.Argon2
import de.mkammerer.argon2.Argon2Factory

object Argon2Helper {
    fun hashPassword(password: String): String {
        val argon2: Argon2 = Argon2Factory.create()
        return argon2.hash(10, 65536, 1, password.toCharArray()) // 10 iterations, 64MB RAM, 1 thread
    }

    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        val argon2: Argon2 = Argon2Factory.create()
        return argon2.verify(hashedPassword, password.toCharArray())
    }
}
