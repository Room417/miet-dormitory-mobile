package ru.miet.dormitory.ui.view.store

import androidx.recyclerview.widget.DiffUtil
import ru.miet.dormitory.ui.model.store.ProductItemModel

class ProductItemDiffCalculator : DiffUtil.ItemCallback<ProductItemModel>() {
    override fun areItemsTheSame(oldItem: ProductItemModel, newItem: ProductItemModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductItemModel, newItem: ProductItemModel): Boolean {
        return oldItem == newItem
    }
}
