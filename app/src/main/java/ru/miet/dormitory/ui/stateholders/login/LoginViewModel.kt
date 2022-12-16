package ru.miet.dormitory.ui.stateholders.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.model.login.UserCredentials
import ru.miet.dormitory.data.repository.login.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {
    val loginPostRequestState = loginRepository.loginPostRequestStateLiveData

    fun makeLoginPostRequest(userCredentials: UserCredentials) {
        viewModelScope.launch {
            loginRepository.makeLoginPostRequest(userCredentials)
        }
    }
}
