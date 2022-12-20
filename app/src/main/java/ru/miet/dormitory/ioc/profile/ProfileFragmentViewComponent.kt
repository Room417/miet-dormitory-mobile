package ru.miet.dormitory.ioc.profile

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import ru.miet.dormitory.databinding.FragmentProfileBinding
import ru.miet.dormitory.ui.view.profile.ProfileFragmentViewController

/**
 * Container for all Fragment-View-scoped classes. This container is created after login fragment's
 * onCreateView and released after onDestroyView.
 */
class ProfileFragmentViewComponent(
    fragmentComponent: ProfileFragmentComponent,
    binding: FragmentProfileBinding,
    lifecycleOwner: LifecycleOwner
) {
    val viewController = ProfileFragmentViewController(
        fragmentComponent.fragment.requireActivity(),
        binding,
        lifecycleOwner,
        fragmentComponent.viewModel,
        fragmentComponent.fragment.findNavController()
    )
}
