package ru.miet.dormitory.ui.model.requests

import java.time.LocalDateTime

data class RequestItemModel(
    val id: Int,
    val title: String,
    val description: String,
    val creationDate: LocalDateTime,
    val isProvided: Boolean,
    val provisionDate: LocalDateTime?
)
