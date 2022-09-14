package com.netguru.commondomain.shop

import com.netguru.commondomain.shop.model.RecentlyViewedProduct
import com.netguru.commondomain.shop.model.RecommendedProduct
import kotlinx.coroutines.Job

interface ShopQuery {

    fun collectRecommendedProducts(collector: (List<RecommendedProduct>) -> Unit): Job

    fun collectRecentlyViewedProducts(collector: (List<RecentlyViewedProduct>) -> Unit): Job

    fun collectSearchedProductName(collector: (String) -> Unit): Job
}
