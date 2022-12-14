package ru.miet.dormitory.data.datasource.notifications

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import ru.miet.dormitory.data.model.notifications.Notification
import ru.miet.dormitory.data.model.notifications.NotificationGetRequestResponseBody
import ru.miet.dormitory.data.model.notifications.NotificationGetRequestState
import java.time.LocalDateTime

class NotificationsDataSource {
    private val responseBody = NotificationGetRequestResponseBody(
        notifications = listOf(
            Notification(
                id = 3,
                date = LocalDateTime.parse("2022-12-09T16:11:00"),
                text = "The \"MIET Dormitory\" app has appeared in the Play Store, making life easier for students. Download and enjoy!",
                authorId = 1
            ),
            Notification(
                id = 2,
                date = LocalDateTime.parse("2022-12-05T13:48:00"),
                text = "In connection with the repair of the showers, please use the shower in the basement of the dormitory.",
                authorId = 1
            ),
            Notification(
                id = 1,
                date = LocalDateTime.parse("2022-12-01T10:15:00"),
                text = "On Wednesday 07.12.2022 at 16:00, bed linen will be changed.",
                authorId = 1
            )
        )
    )

    suspend fun makeNotificationsGetRequest(
        accessToken: String,
        userId: Int,
        notificationsGetRequestStateLiveData: MutableLiveData<NotificationGetRequestState>
    ) {
        delay(1000L)
        notificationsGetRequestStateLiveData.postValue(NotificationGetRequestState(responseBody))
    }
}
