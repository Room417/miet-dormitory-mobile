package ru.miet.dormitory.ui.model.store

import java.time.LocalDateTime

data class ProductItemModel(
    val id: Int,
    val imageCode: Int,
    val title: String,
    val description: String,
    val price: Double,
    val creationDate: LocalDateTime
)
