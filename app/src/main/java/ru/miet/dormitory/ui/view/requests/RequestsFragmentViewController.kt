package ru.miet.dormitory.ui.view.requests

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import ru.miet.dormitory.data.model.requests.Request
import ru.miet.dormitory.databinding.FragmentRequestsBinding
import ru.miet.dormitory.ui.model.requests.RequestItemModel
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel

class RequestsFragmentViewController(
    private val activity: FragmentActivity,
    binding: FragmentRequestsBinding,
    private val adapter: RequestsListAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: RequestsViewModel
) {
    private val swipeRefreshLayout = binding.requestsSwipeRefresh
    private val recyclerView = binding.requestsRecyclerView

    fun setUpViews() {
        setUpRequestsList()
        setUpSwipeToRefresh()
    }

    fun setUpRequestsList() {
        swipeRefreshLayout.isRefreshing = true
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        viewModel.requestsGetRequestState.observe(lifecycleOwner) { response ->
            if (response.error != null) {
                Toast.makeText(activity, response.error, Toast.LENGTH_LONG).show()
            } else {
                val requestsItemModelList =
                    response.responseBody?.requests?.let { requestsList ->
                        requestsList.map { it.transformToItemModel() }
                    }
                adapter.submitList(requestsItemModelList)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    fun setUpSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.makeRequestsGetRequest()
        }
    }

    private fun Request.transformToItemModel() =
        RequestItemModel(id, title, description, creationDate, isProvided, provisionDate)
}
