package ru.miet.dormitory.domain.model

data class DecodedAccessToken(
    val id: Int = -1,
    val timestamp: Long = System.currentTimeMillis()
)
