package ru.miet.dormitory.ui.stateholders.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.profile.ProfileRepository
import ru.miet.dormitory.domain.usecases.DecodeAccessTokenUseCase

class ProfileViewModel(
    private val loginRepository: LoginRepository,
    private val profileRepository: ProfileRepository,
    private val decodeAccessTokenUseCase: DecodeAccessTokenUseCase,
) : ViewModel() {
    val residentGetRequestState = profileRepository.residentGetRequestStateLiveData

    init {
        makeResidentGetRequest()
    }

    override fun onCleared() {
        super.onCleared()
        decodeAccessTokenUseCase.cancelAllOperations()
    }

    fun makeResidentGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            decodeAccessTokenUseCase.decodedAccessToken.value?.id?.let {
                userId = it
            }

            profileRepository.makeResidentGetRequest(accessToken, userId)
        }
    }

    fun clearLoginPostRequestState() {
        loginRepository.clearLoginPostRequestState()
    }
}
