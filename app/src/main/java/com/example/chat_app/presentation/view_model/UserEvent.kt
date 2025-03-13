package com.example.chat_app.presentation.view_model

import com.example.chat_app.domain.model.User

sealed class UserEvent {
    data class EnterUserName(val userName: String): UserEvent()
    data class EnterEmail(val email: String): UserEvent()
    data class EnterPassword(val password: String): UserEvent()
    object RegisterAccount: UserEvent()
}