package ru.miet.dormitory.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.notifications.NotificationsRepository
import ru.miet.dormitory.data.repository.profile.ProfileRepository
import ru.miet.dormitory.data.repository.requests.RequestsRepository
import ru.miet.dormitory.data.repository.store.StoreRepository
import ru.miet.dormitory.domain.usecases.DecodeAccessTokenUseCase
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel
import ru.miet.dormitory.ui.stateholders.profile.ProfileViewModel
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel

class ViewModelFactory(
    private val loginRepository: LoginRepository,
    private val storeRepository: StoreRepository,
    private val requestsRepository: RequestsRepository,
    private val notificationsRepository: NotificationsRepository,
    private val profileRepository: ProfileRepository,
    private val decodeAccessTokenUseCase: DecodeAccessTokenUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(loginRepository)
        StoreViewModel::class.java -> StoreViewModel(
            loginRepository,
            storeRepository,
            decodeAccessTokenUseCase
        )
        RequestsViewModel::class.java -> RequestsViewModel(
            loginRepository,
            requestsRepository,
            decodeAccessTokenUseCase
        )
        NotificationsViewModel::class.java -> NotificationsViewModel(
            loginRepository,
            notificationsRepository,
            decodeAccessTokenUseCase
        )
        ProfileViewModel::class.java -> ProfileViewModel(
            loginRepository,
            profileRepository,
            decodeAccessTokenUseCase
        )
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
