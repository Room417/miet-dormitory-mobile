package ru.miet.dormitory.ioc.store

import androidx.lifecycle.LifecycleOwner
import ru.miet.dormitory.databinding.FragmentStoreBinding
import ru.miet.dormitory.ui.view.store.StoreFragmentViewController

class StoreFragmentViewComponent(
    fragmentComponent: StoreFragmentComponent,
    binding: FragmentStoreBinding,
    lifecycleOwner: LifecycleOwner
) {
    val viewController = StoreFragmentViewController(
        fragmentComponent.fragment.requireActivity(),
        binding,
        fragmentComponent.adapter,
        lifecycleOwner,
        fragmentComponent.viewModel
    )
}
