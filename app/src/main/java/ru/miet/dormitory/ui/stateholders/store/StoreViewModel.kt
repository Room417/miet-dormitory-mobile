package ru.miet.dormitory.ui.stateholders.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.store.StoreRepository
import ru.miet.dormitory.domain.JWTDecodeUseCase

class StoreViewModel(
    private val loginRepository: LoginRepository, private val storeRepository: StoreRepository
) : ViewModel() {
    private val jwtDecodeUseCase = JWTDecodeUseCase()

    val productsGetRequestState = storeRepository.productsGetRequestStateLiveData

    init {
        makeProductsGetRequest()
    }

    fun makeProductsGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                userId = jwtDecodeUseCase.decodeClaim(it, "id").toInt()
            }

            storeRepository.makeProductsGetRequest(accessToken, userId)
        }
    }
}
