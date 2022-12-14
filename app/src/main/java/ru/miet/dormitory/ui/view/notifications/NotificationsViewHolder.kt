package ru.miet.dormitory.ui.view.notifications

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import ru.miet.dormitory.R
import ru.miet.dormitory.ui.model.notifications.NotificationItemModel
import ru.miet.dormitory.ui.stateholders.notifications.NotificationsViewModel
import java.time.format.DateTimeFormatter

class NotificationsViewHolder(
    itemView: View,
    private val viewModel: NotificationsViewModel,
) : RecyclerView.ViewHolder(itemView) {
    /**
     * Note that view references should be cached for faster bind.
     * Bind can be called several time with different data.
     */
    private val date = itemView.findViewById<TextView>(R.id.notification_date)
    private val text = itemView.findViewById<TextView>(R.id.notification_text)
    private val delete = itemView.findViewById<ShapeableImageView>(R.id.notification_delete_icon)

    /**
     * Set the data from the UI-model int actual view.
     */
    fun bind(notification: NotificationItemModel) {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm")
        date.text = notification.date.format(dateTimeFormatter)
        text.text = notification.text
        delete.setOnClickListener { viewModel.onDeletePressed(notification) }
    }
}
