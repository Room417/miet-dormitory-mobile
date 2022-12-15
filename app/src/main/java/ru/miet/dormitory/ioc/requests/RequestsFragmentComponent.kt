package ru.miet.dormitory.ioc.requests

import androidx.fragment.app.Fragment
import ru.miet.dormitory.ioc.ApplicationComponent
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel
import ru.miet.dormitory.ui.view.requests.RequestItemDiffCalculator
import ru.miet.dormitory.ui.view.requests.RequestsListAdapter

class RequestsFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: RequestsViewModel,
) {
    val adapter = RequestsListAdapter(
        viewModel,
        RequestItemDiffCalculator(),
        fragment.requireActivity()
    )
}
