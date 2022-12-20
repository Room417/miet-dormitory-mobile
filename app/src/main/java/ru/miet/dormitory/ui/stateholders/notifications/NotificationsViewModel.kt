package ru.miet.dormitory.ui.stateholders.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.notifications.NotificationsRepository
import ru.miet.dormitory.domain.usecases.DecodeAccessTokenUseCase
import ru.miet.dormitory.ui.model.notifications.NotificationItemModel

class NotificationsViewModel(
    private val loginRepository: LoginRepository,
    private val notificationsRepository: NotificationsRepository,
    private val decodeAccessTokenUseCase: DecodeAccessTokenUseCase,
) : ViewModel() {
    val notificationsGetRequestState = notificationsRepository.notificationGetRequestStateLiveData

    init {
        makeNotificationsGetRequest()
    }

    override fun onCleared() {
        super.onCleared()
        decodeAccessTokenUseCase.cancelAllOperations()
    }

    fun makeNotificationsGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            decodeAccessTokenUseCase.decodedAccessToken.value?.id?.let {
                userId = it
            }

            notificationsRepository.makeNotificationsGetRequest(accessToken, userId)
        }
    }

    fun onDeletePressed(notification: NotificationItemModel) {
        viewModelScope.launch {
            notificationsRepository.deleteNotification(notification.id)
        }
    }
}
