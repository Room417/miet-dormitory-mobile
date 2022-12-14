package ru.miet.dormitory.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.miet.dormitory.data.repository.login.LoginRepository
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel

/**
 * Responsible for creating ViewModels. This class is required if our ViewModels have non-empty
 * constructors, because otherwise the system doesn't know what to pass there.
 */
class ViewModelFactory(
    private val loginRepository: LoginRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        LoginViewModel::class.java -> LoginViewModel(loginRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
