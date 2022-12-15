package ru.miet.dormitory.ui.stateholders.requests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.requests.RequestsRepository
import ru.miet.dormitory.domain.JWTDecodeUseCase

class RequestsViewModel(
    private val loginRepository: LoginRepository, private val requestsRepository: RequestsRepository
) : ViewModel() {
    private val jwtDecodeUseCase = JWTDecodeUseCase()

    val requestsGetRequestState = requestsRepository.requestsGetRequestStateLiveData

    init {
        makeRequestsGetRequest()
    }

    fun makeRequestsGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                userId = jwtDecodeUseCase.decodeClaim(it, "id").toInt()
            }

            requestsRepository.makeRequestsGetRequest(accessToken, userId)
        }
    }
}
