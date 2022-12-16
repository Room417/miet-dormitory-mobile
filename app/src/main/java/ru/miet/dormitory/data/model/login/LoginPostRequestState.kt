package ru.miet.dormitory.data.model.login

data class LoginPostRequestState(
    val responseBody: LoginPostRequestResponseBody? = null,
    val error: Int? = null
)
