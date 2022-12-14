package ru.miet.dormitory.ioc.notifications

import androidx.fragment.app.Fragment
import ru.miet.dormitory.ioc.ApplicationComponent
import ru.miet.dormitory.ui.stateholders.login.LoginViewModel
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel
import ru.miet.dormitory.ui.view.notifications.NotificationItemDiffCalculator
import ru.miet.dormitory.ui.view.notifications.NotificationsListAdapter

/**
 * Container for all fragment-scoped classes. This container is created after login fragment's
 * onCreate and released when the fragment is destroyed.
 */
class NotificationsFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: NotificationsViewModel,
) {
    val adapter = NotificationsListAdapter(viewModel, NotificationItemDiffCalculator())
}
