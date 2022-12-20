package ru.miet.dormitory.ioc

import ru.miet.dormitory.data.datasource.login.LoginDataSource
import ru.miet.dormitory.data.datasource.notifications.NotificationsDataSource
import ru.miet.dormitory.data.datasource.requests.RequestsDataSource
import ru.miet.dormitory.data.datasource.store.ProductsDataSource
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.notifications.NotificationsRepository
import ru.miet.dormitory.data.repository.requests.RequestsRepository
import ru.miet.dormitory.data.repository.store.StoreRepository
import ru.miet.dormitory.domain.usecases.DecodeAccessTokenUseCase

/**
 * Container that stores all classes, that are Application-scoped,
 * i.e. for classes that live as long as application itself.
 */
class ApplicationComponent {
    private val loginDataSource = LoginDataSource()
    private val productsDataSource = ProductsDataSource()
    private val requestsDataSource = RequestsDataSource()
    private val notificationsDataSource = NotificationsDataSource()

    private val loginRepository = LoginRepository(loginDataSource)
    private val storeRepository = StoreRepository(productsDataSource)
    private val requestsRepository = RequestsRepository(requestsDataSource)
    private val notificationsRepository = NotificationsRepository(notificationsDataSource)

    private val decodeAccessTokenUseCase = DecodeAccessTokenUseCase(loginRepository)

    val viewModelFactory = ViewModelFactory(
        loginRepository,
        storeRepository,
        requestsRepository,
        notificationsRepository,
        decodeAccessTokenUseCase
    )
}
