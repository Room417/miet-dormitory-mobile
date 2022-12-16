package ru.miet.dormitory.data.model.store

data class ProductsGetRequestState(
    val responseBody: ProductsGetRequestResponseBody? = null,
    val error: Int? = null
)
