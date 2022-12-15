package ru.miet.dormitory.ui.view.requests

import android.app.Activity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import ru.miet.dormitory.R
import ru.miet.dormitory.ui.model.requests.RequestItemModel
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel
import java.time.format.DateTimeFormatter

class RequestsViewHolder(
    itemView: View,
    private val viewModel: RequestsViewModel,
    private val activity: Activity
) : RecyclerView.ViewHolder(itemView) {

    private val title = itemView.findViewById<TextView>(R.id.request_title)
    private val description = itemView.findViewById<TextView>(R.id.request_description)
    private val creationDate = itemView.findViewById<TextView>(R.id.request_creation_date)
    private val provisionDateLinearLayout =
        itemView.findViewById<LinearLayout>(R.id.request_provision_date_linear_layout)
    private val provisionDate = itemView.findViewById<TextView>(R.id.request_provision_date)
    private val status = itemView.findViewById<TextView>(R.id.request_status)

    fun bind(request: RequestItemModel) {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

        title.text = request.title
        description.text = request.description
        creationDate.text = request.creationDate.format(dateTimeFormatter)
        request.provisionDate?.let {
            provisionDateLinearLayout.visibility = View.VISIBLE
            provisionDate.text = it.format(dateTimeFormatter)
        }
        when (request.isProvided) {
            true -> {
                status.text = activity.getText(R.string.requests_request_was_provided)
                status.setBackgroundColor(ActivityCompat.getColor(activity, R.color.green))
            }
            false -> {
                status.text = activity.getText(R.string.requests_request_was_not_provided)
                status.setBackgroundColor(ActivityCompat.getColor(activity, R.color.red))
            }
        }
    }
}
