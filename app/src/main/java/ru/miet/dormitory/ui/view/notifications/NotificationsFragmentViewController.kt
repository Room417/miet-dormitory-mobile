package ru.miet.dormitory.ui.view.notifications

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import ru.miet.dormitory.data.model.notifications.Notification
import ru.miet.dormitory.databinding.FragmentNotificationsBinding
import ru.miet.dormitory.ui.model.notifications.NotificationItemModel
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel

class NotificationsFragmentViewController(
    private val activity: FragmentActivity,
    binding: FragmentNotificationsBinding,
    private val adapter: NotificationsListAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: NotificationsViewModel
) {
    private val swipeRefreshLayout = binding.notificationsSwipeRefresh
    private val recyclerView = binding.notificationsRecyclerView

    fun setUpViews() {
        setUpNotificationsList()
        setUpSwipeToRefresh()
    }

    fun setUpNotificationsList() {
        swipeRefreshLayout.isRefreshing = true
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
        viewModel.notificationsGetRequestState.observe(lifecycleOwner) { response ->
            if (response.error != null) {
                Toast.makeText(activity, response.error, Toast.LENGTH_LONG).show()
            } else {
                val notificationsItemModelList =
                    response.responseBody?.notifications?.let { notificationList ->
                        notificationList.map { it.transformToItemModel() }
                    }
                adapter.submitList(notificationsItemModelList)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    fun setUpSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.makeNotificationsGetRequest()
        }
    }

    private fun Notification.transformToItemModel() =
        NotificationItemModel(id, date, title, description, isViewed)
}
