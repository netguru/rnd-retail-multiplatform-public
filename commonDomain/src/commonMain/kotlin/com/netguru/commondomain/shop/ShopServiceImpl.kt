package com.netguru.commondomain.shop

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.ShopDataSource
import com.netguru.commondomain.product.ProductDetailsService
import com.netguru.commondomain.router.MainRouter
import com.soywiz.klock.DateTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal class ShopServiceImpl(
    private val shopStore: Store<ShopState>,
    private val shopDataSource: ShopDataSource,
    private val mainRouter: MainRouter,
    private val productDetailsService: ProductDetailsService,
    private val scope: CoroutineScope
) : ShopService, CoroutineScope by scope {

    override fun loadData() = launch {
        shopStore.update {
            it.copy(loading = Loading.InProgress)
        }
        runCatching {
            shopDataSource.getProducts()
        }.onSuccess { products ->
            shopStore.update {
                it.copy(
                    loading = Loading.Idle,
                    products = products
                )
            }
        }.onFailure { error ->
            shopStore.update {
                it.copy(loading = Loading.Failed(error))
            }
        }
    }

    override fun openProductDetails(productId: String) = launch {
        updateProductWasViewed(productId)
        productDetailsService.loadData(productId)
        withContext(Dispatchers.Main) {
            mainRouter.toProductDetails()
        }
    }

    override fun searchProduct(productName: String) = launch {
        shopStore.update {
            it.copy(searchedProductName = productName)
        }
    }

    private fun updateProductWasViewed(productId: String) {
        shopStore.update { state ->
            val updatedProducts = state.products.map {
                if (it.id == productId) it.copy(lastViewed = DateTime.now()) else it
            }
            state.copy(products = updatedProducts)
        }
    }
}
