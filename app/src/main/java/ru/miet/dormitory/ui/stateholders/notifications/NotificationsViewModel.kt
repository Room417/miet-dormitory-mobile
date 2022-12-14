package ru.miet.dormitory.ui.stateholders.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.miet.dormitory.data.model.notifications.Notification
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.notifications.NotificationsRepository
import ru.miet.dormitory.domain.JWTDecodeUseCase
import ru.miet.dormitory.ui.model.notifications.NotificationItemModel

class NotificationsViewModel(
    private val loginRepository: LoginRepository,
    private val notificationsRepository: NotificationsRepository
) : ViewModel() {
    private val jwtDecodeUseCase = JWTDecodeUseCase()

    val notificationsGetRequestState = notificationsRepository.notificationGetRequestStateLiveData

    init {
        makeNotificationsGetRequest()
    }

    fun makeNotificationsGetRequest() {
        viewModelScope.launch {
            var accessToken: String = ""
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                accessToken = it
            }

            var userId: Int = -1
            loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
                userId = jwtDecodeUseCase.decodeClaim(it, "id").toInt()
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
