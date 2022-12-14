package ru.miet.dormitory.ioc

import ru.miet.dormitory.data.datasource.login.LoginDataSource
import ru.miet.dormitory.data.repository.login.LoginRepository

/**
 * Container that stores all classes, that are Application-scoped,
 * i.e. for classes that live as long as application itself.
 */
class ApplicationComponent {
    private val loginDataSource = LoginDataSource()
    private val loginRepository = LoginRepository(loginDataSource)

    val viewModelFactory = ViewModelFactory(loginRepository)
}
