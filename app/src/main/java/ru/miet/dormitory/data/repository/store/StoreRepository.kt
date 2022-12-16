package ru.miet.dormitory.data.repository.store

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miet.dormitory.data.datasource.store.ProductsDataSource
import ru.miet.dormitory.data.model.store.ProductsGetRequestState

class StoreRepository(private val dataSource: ProductsDataSource) {
    private val _productsGetRequestStateLiveData = MutableLiveData<ProductsGetRequestState>()
    val productsGetRequestStateLiveData: LiveData<ProductsGetRequestState> =
        _productsGetRequestStateLiveData

    @MainThread
    suspend fun makeProductsGetRequest(accessToken: String, userId: Int) {
        withContext(Dispatchers.IO) {
            dataSource.makeProductsGetRequest(
                accessToken,
                userId,
                _productsGetRequestStateLiveData
            )
        }
    }
}
