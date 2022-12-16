package ru.miet.dormitory.ioc.notifications

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import ru.miet.dormitory.databinding.FragmentNotificationsBinding
import ru.miet.dormitory.ui.view.notifications.NotificationsFragmentViewController

class NotificationsFragmentViewComponent(
    fragmentComponent: NotificationsFragmentComponent,
    binding: FragmentNotificationsBinding,
    lifecycleOwner: LifecycleOwner
) {
    val viewController = NotificationsFragmentViewController(
        fragmentComponent.fragment.requireActivity(),
        binding,
        fragmentComponent.adapter,
        lifecycleOwner,
        fragmentComponent.viewModel
    )
}
