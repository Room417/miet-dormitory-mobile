package ru.miet.dormitory.ui.stateholders.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.requests.RequestsRepository
import ru.miet.dormitory.domain.usecases.DecodeAccessTokenUseCase

class RequestsViewModel(
    private val loginRepository: LoginRepository,
    private val requestsRepository: RequestsRepository,
    private val decodeAccessTokenUseCase: DecodeAccessTokenUseCase
) : ViewModel() {
    val requestsGetRequestState = requestsRepository.requestsGetRequestStateLiveData

    init {
        makeRequestsGetRequest()
    }

    override fun onCleared() {
        super.onCleared()
        decodeAccessTokenUseCase.cancelAllOperations()
    }

    fun makeRequestsGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            decodeAccessTokenUseCase.decodedAccessToken.value?.id?.let {
                userId = it
            }

            requestsRepository.makeRequestsGetRequest(accessToken, userId)
        }
    }
}
