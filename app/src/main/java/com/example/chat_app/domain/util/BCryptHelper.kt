package com.example.chat_app.domain.util

import at.favre.lib.crypto.bcrypt.BCrypt

object BCryptHelper {
    fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    fun verifyPassword(password: String, hashPassword: String): Boolean {
        return BCrypt.verifyer().verify(password.toCharArray(), hashPassword).verified
    }
}