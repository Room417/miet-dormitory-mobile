package ru.miet.dormitory.data.repository.profile

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miet.dormitory.data.datasource.profile.ResidentDataSource
import ru.miet.dormitory.data.model.profile.ResidentGetRequestState

class ProfileRepository(private val dataSource: ResidentDataSource) {
    private val _residentGetRequestStateLiveData =
        MutableLiveData<ResidentGetRequestState>()
    val residentGetRequestStateLiveData: LiveData<ResidentGetRequestState> =
        _residentGetRequestStateLiveData

    @MainThread
    suspend fun makeResidentGetRequest(accessToken: String, userId: Int) {
        withContext(Dispatchers.IO) {
            dataSource.makeResidentGetRequest(
                accessToken,
                userId,
                _residentGetRequestStateLiveData
            )
        }
    }
}
