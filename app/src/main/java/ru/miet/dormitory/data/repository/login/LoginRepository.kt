package ru.miet.dormitory.data.repository.login

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miet.dormitory.data.datasource.login.LoginDataSource
import ru.miet.dormitory.data.model.login.LoginPostRequestState
import ru.miet.dormitory.data.model.login.UserCredentials

/**
 * Contains the logic for getting and updating the user's credentials and the token received from
 * the server.
 *
 * Stores the server's last response to a login request and provides a way to monitor its changes.
 */
class LoginRepository(private val dataSource: LoginDataSource) {
    private val _loginPostRequestStateLiveData = MutableLiveData<LoginPostRequestState>()
    val loginPostRequestStateLiveData: LiveData<LoginPostRequestState> =
        _loginPostRequestStateLiveData

    @MainThread
    suspend fun makeLoginPostRequest(userCredentials: UserCredentials) {
        withContext(Dispatchers.IO) {
            dataSource.makeLoginPostRequest(
                userCredentials,
                _loginPostRequestStateLiveData
            )
        }
    }

    fun clearLoginPostRequestState() {
        _loginPostRequestStateLiveData.value = LoginPostRequestState()
    }
}
