package ru.miet.dormitory.data.datasource.requests

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import ru.miet.dormitory.data.model.requests.Request
import ru.miet.dormitory.data.model.requests.RequestsGetRequestResponseBody
import ru.miet.dormitory.data.model.requests.RequestsGetRequestState
import java.time.LocalDateTime

class RequestsDataSource {
    private val responseBody = RequestsGetRequestResponseBody(
        requests = listOf(
            Request(
                id = 2,
                title = "Сломан водопроводный кран",
                description = "На 3 этаже 15 корпуса сломан один из кранов в прачечной. Почините, пожалуйста.",
                creationDate = LocalDateTime.parse("2022-12-06T08:54:00"),
                isProvided = false,
                provisionDate = null,
                authorId = 1
            ),
            Request(
                id = 1,
                title = "Замена лампочки в комнате",
                description = "В комнате 417 перегорела лампа. Просьба заменить.",
                creationDate = LocalDateTime.parse("2022-12-01T16:11:00"),
                isProvided = true,
                provisionDate = LocalDateTime.parse("2022-12-02T12:45:00"),
                authorId = 1
            )
        )
    )

    suspend fun makeRequestsGetRequest(
        accessToken: String,
        userId: Int,
        requestsGetRequestStateLiveData: MutableLiveData<RequestsGetRequestState>
    ) {
        delay(1000L)
        requestsGetRequestStateLiveData.postValue(RequestsGetRequestState(responseBody))
    }
}
