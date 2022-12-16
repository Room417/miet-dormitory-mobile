package ru.miet.dormitory.ui.view.store

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.miet.dormitory.R
import ru.miet.dormitory.ui.model.store.ProductItemModel
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel
import java.time.format.DateTimeFormatter

class ProductsViewHolder(
    itemView: View,
    private val viewModel: StoreViewModel,
    private val activity: Activity
) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.findViewById<ImageView>(R.id.store_product_image)
    private val title = itemView.findViewById<TextView>(R.id.store_product_title)
    private val description = itemView.findViewById<TextView>(R.id.store_product_description)
    private val price = itemView.findViewById<TextView>(R.id.store_product_price)
    private val creationDate = itemView.findViewById<TextView>(R.id.store_product_creation_date)

    fun bind(product: ProductItemModel) {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")

        image.setImageResource(product.imageCode)
        title.text = product.title
        description.text = product.description
        price.text = product.price.toString().plus("₽")
        creationDate.text = product.creationDate.format(dateTimeFormatter)
    }
}
