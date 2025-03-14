package com.example.chat_app.domain.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class User(
    @Id var id: Long = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var image: String? = null,
    var phoneNumber: Int = 0,
    var role: String = ""
)