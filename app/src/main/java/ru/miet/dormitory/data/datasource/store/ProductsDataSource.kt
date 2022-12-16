package ru.miet.dormitory.data.datasource.store

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import ru.miet.dormitory.R
import ru.miet.dormitory.data.model.store.Product
import ru.miet.dormitory.data.model.store.ProductsGetRequestResponseBody
import ru.miet.dormitory.data.model.store.ProductsGetRequestState
import java.time.LocalDateTime

class ProductsDataSource {
    private val responseBody = ProductsGetRequestResponseBody(
        products = listOf(
            Product(
                id = 3,
                authorId = 1,
                imageId = R.raw.garage_image,
                title = "Гараж",
                description = "Продам гараж",
                price = 10000.0,
                creationDate = LocalDateTime.parse("2022-12-07T09:54:00"),
                isPublished = true
            ),
            Product(
                id = 2,
                authorId = 1,
                imageId = R.raw.display_image,
                title = "Samsung SyncMaster 943NW",
                description = "В хорошем состоянии!",
                price = 2000.0,
                creationDate = LocalDateTime.parse("2022-12-05T13:11:00"),
                isPublished = true
            ),
            Product(
                id = 1,
                authorId = 1,
                imageId = R.raw.skates_image,
                title = "Коньки",
                description = "Мужские, 42 размер.",
                price = 1500.0,
                creationDate = LocalDateTime.parse("2022-12-03T19:01:00"),
                isPublished = true
            )
        )
    )

    suspend fun makeProductsGetRequest(
        accessToken: String,
        userId: Int,
        productsGetRequestStateLiveData: MutableLiveData<ProductsGetRequestState>
    ) {
        delay(1000L)
        productsGetRequestStateLiveData.postValue(ProductsGetRequestState(responseBody))
    }
}
