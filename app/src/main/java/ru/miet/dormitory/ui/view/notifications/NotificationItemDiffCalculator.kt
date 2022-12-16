package ru.miet.dormitory.ui.view.notifications

import androidx.recyclerview.widget.DiffUtil
import ru.miet.dormitory.ui.model.notifications.NotificationItemModel

class NotificationItemDiffCalculator : DiffUtil.ItemCallback<NotificationItemModel>() {
    override fun areItemsTheSame(
        oldItem: NotificationItemModel,
        newItem: NotificationItemModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: NotificationItemModel,
        newItem: NotificationItemModel
    ): Boolean {
        return oldItem == newItem
    }
}
