package ru.miet.dormitory.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.notifications.NotificationsRepository
import ru.miet.dormitory.data.repository.requests.RequestsRepository
import ru.miet.dormitory.data.repository.store.StoreRepository
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel

/**
 * Responsible for creating ViewModels. This class is required if our ViewModels have non-empty
 * constructors, because otherwise the system doesn't know what to pass there.
 */
class ViewModelFactory(
    private val loginRepository: LoginRepository,
    private val storeRepository: StoreRepository,
    private val requestsRepository: RequestsRepository,
    private val notificationsRepository: NotificationsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(loginRepository)
        StoreViewModel::class.java -> StoreViewModel(loginRepository, storeRepository)
        RequestsViewModel::class.java -> RequestsViewModel(loginRepository, requestsRepository)
        NotificationsViewModel::class.java -> NotificationsViewModel(loginRepository, notificationsRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
