package ru.miet.dormitory.data.model.notifications

import java.time.LocalDateTime

data class Notification(
    val id: Int,
    val date: LocalDateTime,
    val title: String,
    val description: String,
    val isViewed: Boolean = false,
    val authorId: Int
)
