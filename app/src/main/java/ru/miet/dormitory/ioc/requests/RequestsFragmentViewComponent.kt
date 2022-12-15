package ru.miet.dormitory.ioc.requests

import androidx.lifecycle.LifecycleOwner
import ru.miet.dormitory.databinding.FragmentRequestsBinding
import ru.miet.dormitory.ui.view.requests.RequestsFragmentViewController

class RequestsFragmentViewComponent(
    fragmentComponent: RequestsFragmentComponent,
    binding: FragmentRequestsBinding,
    lifecycleOwner: LifecycleOwner
) {
    val viewController = RequestsFragmentViewController(
        fragmentComponent.fragment.requireActivity(),
        binding,
        fragmentComponent.adapter,
        lifecycleOwner,
        fragmentComponent.viewModel
    )
}
