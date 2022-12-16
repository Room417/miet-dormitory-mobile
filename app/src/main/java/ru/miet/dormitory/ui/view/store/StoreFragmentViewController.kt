package ru.miet.dormitory.ui.view.store

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import ru.miet.dormitory.data.model.store.Product
import ru.miet.dormitory.databinding.FragmentStoreBinding
import ru.miet.dormitory.ui.model.store.ProductItemModel
import ru.miet.dormitory.ui.stateholders.store.StoreViewModel

class StoreFragmentViewController(
    private val activity: FragmentActivity,
    binding: FragmentStoreBinding,
    private val adapter: ProductsListAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: StoreViewModel
) {
    private val swipeRefreshLayout = binding.storeSwipeRefresh
    private val recyclerView = binding.storeRecyclerView

    fun setUpViews() {
        setUpRequestsList()
        setUpSwipeToRefresh()
    }

    fun setUpRequestsList() {
        swipeRefreshLayout.isRefreshing = true
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter
        viewModel.productsGetRequestState.observe(lifecycleOwner) { response ->
            if (response.error != null) {
                Toast.makeText(activity, response.error, Toast.LENGTH_LONG).show()
            } else {
                val productsItemModelList =
                    response.responseBody?.products?.let { requestsList ->
                        requestsList.map { it.transformToItemModel() }
                    }
                adapter.submitList(productsItemModelList)
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    fun setUpSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.makeProductsGetRequest()
        }
    }

    private fun Product.transformToItemModel() =
        ProductItemModel(id, imageId, title, description, price, creationDate)
}
