package ru.miet.dormitory.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.*
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.domain.model.DecodedAccessToken

class DecodeAccessTokenUseCase(private val loginRepository: LoginRepository) {
    private val _decodedAccessToken = MediatorLiveData<DecodedAccessToken>()
    val decodedAccessToken: LiveData<DecodedAccessToken> = _decodedAccessToken

    private val combineCoroutineScope = CoroutineScope(Job() + Dispatchers.Default)

    init {
        _decodedAccessToken.addSource(loginRepository.loginPostRequestStateLiveData) {
            updateDecodedAccessTokenLiveData()
        }
    }

    fun cancelAllOperations() {
        combineCoroutineScope.cancel()
    }

    private fun updateDecodedAccessTokenLiveData() {
        combineCoroutineScope.launch {
            _decodedAccessToken.postValue(decodeAccessToken())
        }
    }

    private fun decodeAccessToken(): DecodedAccessToken {
        val accessToken: String
        loginRepository.loginPostRequestStateLiveData.value?.responseBody?.accessToken?.let {
            accessToken = it
            val jwt = JWT(accessToken)
            return DecodedAccessToken(
                id = jwt.getClaim("id").asInt() ?: -1,
                timestamp = jwt.getClaim("iat").asLong() ?: System.currentTimeMillis()
            )
        }
        return DecodedAccessToken()
    }
}
