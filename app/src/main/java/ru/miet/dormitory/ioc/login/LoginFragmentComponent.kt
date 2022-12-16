package ru.miet.dormitory.ioc.login

import androidx.fragment.app.Fragment
import ru.miet.dormitory.ioc.ApplicationComponent
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel

/**
 * Container for all fragment-scoped classes. This container is created after login fragment's
 * onCreate and released when the fragment is destroyed.
 */
class LoginFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: LoginViewModel,
) {
}
