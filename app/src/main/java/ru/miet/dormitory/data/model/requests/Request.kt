package ru.miet.dormitory.data.model.requests

import java.time.LocalDateTime

data class Request(
    val id: Int,
    val authorId: Int,
    val title: String,
    val description: String,
    val creationDate: LocalDateTime,
    val isProvided: Boolean,
    val provisionDate: LocalDateTime?
)
