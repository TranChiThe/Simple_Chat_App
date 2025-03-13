package com.example.chat_app.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat_app.domain.model.User
import com.example.chat_app.domain.use_cases.User.UserUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUserCase: UserUserCase
): ViewModel() {

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName
    private val _email = mutableStateOf("")
    val email: State<String> = _email
    private val _password = mutableStateOf("")
    val password: State<String> = _password
    private val _userId = mutableStateOf<Long?>(null)
    
    private val _userFlow = MutableStateFlow<List<User>>(emptyList())
    val userFlow: StateFlow<List<User>> = _userFlow

    init {
        getAllUser()
    }

    fun getAllUser() {
        viewModelScope.launch {
            userUserCase.getAllUser().collect{users ->
                _userFlow.value = users
            }
        }
    }

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.EnterUserName -> {
                _userName.value = event.userName
            }

            is UserEvent.EnterEmail -> {
                _email.value = event.email
            }

            is UserEvent.EnterPassword -> {
                _password.value = event.password
            }

            is UserEvent.RegisterAccount -> {
                viewModelScope.launch {
                    val user = User(
                        name = _userName.value,
                        email = _email.value,
                        password = _password.value,
                        role = "1"
                    )
                    userUserCase.registerAccount(user)
                }
            }
        }
    }
}