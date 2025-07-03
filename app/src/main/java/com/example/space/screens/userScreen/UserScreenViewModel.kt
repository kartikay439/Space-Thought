package com.example.space.screens.userScreen

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.dataStore.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UserScreenViewModel(userData: UserData) : ViewModel() {
    private val _user = MutableStateFlow<User>(User("", ""))
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            val name = userData.userName.first{ it.isNotBlank() }
            val email = userData.userEmail.first{ it.isNotBlank() }

            _user.value = User(name, email)

            userData.userName.combine(userData.userEmail) { name, email ->
                User(name, email)
            }.collect { user ->
                _user.value = user
            }
        }
    }

}

data class User(
    val name: String,
    val email: String
)