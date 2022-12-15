package ru.miet.dormitory.data.repository.requests

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miet.dormitory.data.datasource.requests.RequestsDataSource
import ru.miet.dormitory.data.model.requests.RequestsGetRequestState

class RequestsRepository(private val dataSource: RequestsDataSource) {
    private val _requestsGetRequestStateLiveData = MutableLiveData<RequestsGetRequestState>()
    val requestsGetRequestStateLiveData: LiveData<RequestsGetRequestState> =
        _requestsGetRequestStateLiveData

    @MainThread
    suspend fun makeRequestsGetRequest(accessToken: String, userId: Int) {
        withContext(Dispatchers.IO) {
            dataSource.makeRequestsGetRequest(
                accessToken,
                userId,
                _requestsGetRequestStateLiveData
            )
        }
    }
}
