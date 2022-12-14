package ru.miet.dormitory.ioc

import ru.miet.dormitory.data.datasource.login.LoginDataSource
import ru.miet.dormitory.data.datasource.notifications.NotificationsDataSource
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.data.repository.notifications.NotificationsRepository

/**
 * Container that stores all classes, that are Application-scoped,
 * i.e. for classes that live as long as application itself.
 */
class ApplicationComponent {
    private val loginDataSource = LoginDataSource()
    private val notificationsDataSource = NotificationsDataSource()

    private val loginRepository = LoginRepository(loginDataSource)
    private val notificationsRepository = NotificationsRepository(notificationsDataSource)

    val viewModelFactory = ViewModelFactory(loginRepository, notificationsRepository)
}
