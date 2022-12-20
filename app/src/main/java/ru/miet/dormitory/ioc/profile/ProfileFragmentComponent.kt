package ru.miet.dormitory.ioc.profile

import androidx.fragment.app.Fragment
import ru.miet.dormitory.ioc.ApplicationComponent
import ru.miet.dormitory.ui.stateholders.profile.ProfileViewModel

/**
 * Container for all fragment-scoped classes. This container is created after login fragment's
 * onCreate and released when the fragment is destroyed.
 */
class ProfileFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: ProfileViewModel,
) {
}
