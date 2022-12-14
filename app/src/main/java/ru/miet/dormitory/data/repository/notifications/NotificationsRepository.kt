package ru.miet.dormitory.data.repository.notifications

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miet.dormitory.data.datasource.notifications.NotificationsDataSource
import ru.miet.dormitory.data.model.notifications.NotificationGetRequestResponseBody
import ru.miet.dormitory.data.model.notifications.NotificationGetRequestState

class NotificationsRepository(private val dataSource: NotificationsDataSource) {
    private val _notificationGetRequestStateLiveData =
        MutableLiveData<NotificationGetRequestState>()
    val notificationGetRequestStateLiveData: LiveData<NotificationGetRequestState> =
        _notificationGetRequestStateLiveData

    @MainThread
    suspend fun makeNotificationsGetRequest(accessToken: String, userId: Int) {
        withContext(Dispatchers.IO) {
            dataSource.makeNotificationsGetRequest(
                accessToken,
                userId,
                _notificationGetRequestStateLiveData
            )
        }
    }

    suspend fun deleteNotification(id: Int) {
        withContext(Dispatchers.Default) {
            val newNotificationGetRequestState = NotificationGetRequestState(
                NotificationGetRequestResponseBody(
                    notifications = notificationGetRequestStateLiveData.value?.responseBody?.notifications.orEmpty()
                        .filter { it.id != id })
            )
            _notificationGetRequestStateLiveData.postValue(newNotificationGetRequestState)
        }
    }
}
