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
                title = "Состоялся релиз приложения \"MIET Dormitory\"!",
                description = "В Play Store появилось приложение \"MIET Dormitory\", облегчающее жизнь студентам. Скачивайте и наслаждайтесь!",
                authorId = 1
            ),
            Notification(
                id = 2,
                date = LocalDateTime.parse("2022-12-05T13:48:00"),
                title = "Ремонт в общежитии.",
                description = "Комната для подготовки, располагающаяся в переходе между 13 и 15 корпусами, закрыта на ремонт.",
                authorId = 1
            ),
            Notification(
                id = 1,
                date = LocalDateTime.parse("2022-12-01T10:15:00"),
                title = "Смена постельного белья.",
                description = "Смена постельного белья будет производиться в среду 07.12.2022 в 16:00.",
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
