package ru.miet.dormitory.ui.view.requests

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.miet.dormitory.R
import ru.miet.dormitory.ui.model.requests.RequestItemModel
import ru.miet.dormitory.ui.stateholders.requests.RequestsViewModel

class RequestsListAdapter(
    private val viewModel: RequestsViewModel,
    articleDiffCalculator: RequestItemDiffCalculator,
    private val activity: Activity
) : ListAdapter<RequestItemModel, RequestsViewHolder>(articleDiffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.request_item,
            parent,
            false
        )
        return RequestsViewHolder(itemView, viewModel, activity)
    }

    override fun onBindViewHolder(holder: RequestsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
