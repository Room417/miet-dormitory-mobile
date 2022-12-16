package ru.miet.dormitory.ui.view.store

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.miet.dormitory.R
import ru.miet.dormitory.ui.model.store.ProductItemModel
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel

class ProductsListAdapter(
    private val viewModel: StoreViewModel,
    articleDiffCalculator: ProductItemDiffCalculator,
    private val activity: Activity
) : ListAdapter<ProductItemModel, ProductsViewHolder>(articleDiffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.store_product_item,
            parent,
            false
        )
        return ProductsViewHolder(itemView, viewModel, activity)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
