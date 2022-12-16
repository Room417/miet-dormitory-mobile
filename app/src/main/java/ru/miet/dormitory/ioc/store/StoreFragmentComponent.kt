package ru.miet.dormitory.ioc.store

import androidx.fragment.app.Fragment
import ru.miet.dormitory.ioc.ApplicationComponent
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel
import ru.miet.dormitory.ui.view.store.ProductItemDiffCalculator
import ru.miet.dormitory.ui.view.store.ProductsListAdapter

class StoreFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: StoreViewModel,
) {
    val adapter = ProductsListAdapter(
        viewModel,
        ProductItemDiffCalculator(),
        fragment.requireActivity()
    )
}
