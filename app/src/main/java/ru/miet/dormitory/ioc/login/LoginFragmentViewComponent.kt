package ru.miet.dormitory.ioc.login

import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import ru.miet.dormitory.databinding.FragmentLoginBinding
import ru.miet.dormitory.ui.view.login.LoginFragmentViewController

/**
 * Container for all Fragment-View-scoped classes. This container is created after login fragment's
 * onCreateView and released after onDestroyView.
 */
class LoginFragmentViewComponent(
    fragmentComponent: LoginFragmentComponent,
    binding: FragmentLoginBinding,
    lifecycleOwner: LifecycleOwner
) {
    val viewController = LoginFragmentViewController(
        fragmentComponent.fragment.requireActivity(),
        binding,
        lifecycleOwner,
        fragmentComponent.viewModel,
        fragmentComponent.fragment.findNavController()
    )
}
