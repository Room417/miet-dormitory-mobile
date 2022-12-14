package ru.miet.dormitory.data.datasource.login

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import ru.miet.dormitory.data.model.login.LoginPostRequestResponseBody
import ru.miet.dormitory.data.model.login.LoginPostRequestState
import ru.miet.dormitory.data.model.login.UserCredentials

/**
 * Sends login requests to the server and receives a response from it.
 */
class LoginDataSource {
    private val responseBody = LoginPostRequestResponseBody(
        accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjcxMDE5MjAwfQ.hnZvRqVkKDVf3-g4RCGNOHfLCz9rsFzs1rX4aCM64J8",
        refreshToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiaWF0IjoxNjcxMTA1NjAwfQ.9W2_XePCJxdUO0LLdXkYP7XRtDEhcTfmCPjv6eNnkE8"
    )

    /**
     * Simulates sending a login post request with delay.
     */
    suspend fun makeLoginPostRequest(
        userCredentials: UserCredentials,
        loginPostRequestStateLiveData: MutableLiveData<LoginPostRequestState>
    ) {
        delay(2000L)
        loginPostRequestStateLiveData.postValue(
            LoginPostRequestState(
                responseBody = responseBody,
                error = null
            )
        )
    }
}
