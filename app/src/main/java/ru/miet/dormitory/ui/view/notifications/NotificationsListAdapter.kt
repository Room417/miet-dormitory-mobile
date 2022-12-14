package ru.miet.dormitory.ui.view.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.miet.dormitory.R
import ru.miet.dormitory.ui.model.notifications.NotificationItemModel
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel

class NotificationsListAdapter(
    private val viewModel: NotificationsViewModel,
    articleDiffCalculator: NotificationItemDiffCalculator,
) : ListAdapter<NotificationItemModel, NotificationsViewHolder>(articleDiffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.notification_item,
            parent,
            false
        )
        return NotificationsViewHolder(itemView, viewModel)
    }

    override fun onBindViewHolder(holder: NotificationsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
