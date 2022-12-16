package ru.miet.dormitory.ui.view.requests

import androidx.recyclerview.widget.DiffUtil
import ru.miet.dormitory.ui.model.requests.RequestItemModel

class RequestItemDiffCalculator : DiffUtil.ItemCallback<RequestItemModel>() {
    override fun areItemsTheSame(oldItem: RequestItemModel, newItem: RequestItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RequestItemModel, newItem: RequestItemModel): Boolean {
        return oldItem == newItem
    }
}
