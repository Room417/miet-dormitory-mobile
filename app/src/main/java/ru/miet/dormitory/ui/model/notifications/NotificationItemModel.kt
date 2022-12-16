package ru.miet.dormitory.ui.model.notifications

import java.time.LocalDateTime

data class NotificationItemModel(
    val id: Int,
    val date: LocalDateTime,
    val title: String,
    val description: String,
    val isViewed: Boolean = false
)
