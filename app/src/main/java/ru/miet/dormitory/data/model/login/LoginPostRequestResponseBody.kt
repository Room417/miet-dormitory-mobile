package ru.miet.dormitory.data.model.login

data class LoginPostRequestResponseBody(
    val accessToken: String,
    val refreshToken: String
)
