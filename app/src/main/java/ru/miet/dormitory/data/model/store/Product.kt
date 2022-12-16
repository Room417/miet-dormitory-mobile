package ru.miet.dormitory.data.model.store

import java.time.LocalDateTime

data class Product(
    val id: Int,
    val authorId: Int,
    val imageId: Int,
    val title: String,
    val description: String,
    val price: Double,
    val creationDate: LocalDateTime,
    val isPublished: Boolean
)
