package ru.miet.dormitory.ui.stateholders.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.store.StoreRepository
import ru.miet.dormitory.domain.usecases.DecodeAccessTokenUseCase

class StoreViewModel(
    private val loginRepository: LoginRepository,
    private val storeRepository: StoreRepository,
    private val decodeAccessTokenUseCase: DecodeAccessTokenUseCase,
) : ViewModel() {
    val productsGetRequestState = storeRepository.productsGetRequestStateLiveData

    init {
        makeProductsGetRequest()
    }

    override fun onCleared() {
        super.onCleared()
        decodeAccessTokenUseCase.cancelAllOperations()
    }

    fun makeProductsGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            decodeAccessTokenUseCase.decodedAccessToken.value?.id?.let {
                userId = it
            }

            storeRepository.makeProductsGetRequest(accessToken, userId)
        }
    }
}
