package com.netguru.retail.android.mocks

import com.netguru.commondomain.shop.ShopQuery
import com.netguru.commondomain.shop.model.RecentlyViewedProduct
import com.netguru.commondomain.shop.model.RecommendedProduct
import kotlinx.coroutines.Job

class ShopQueryMock : ShopQuery {

    var recommendedProducts: List<RecommendedProduct> = emptyList()
    var recentlyViewedProducts: List<RecentlyViewedProduct> = emptyList()
    var searchedProductName: String = ""

    override fun collectRecommendedProducts(
        collector: (List<RecommendedProduct>) -> Unit
    ): Job {
        collector(recommendedProducts)
        return Job()
    }

    override fun collectRecentlyViewedProducts(
        collector: (List<RecentlyViewedProduct>) -> Unit
    ): Job {
        collector(recentlyViewedProducts)
        return Job()
    }

    override fun collectSearchedProductName(collector: (String) -> Unit): Job {
        collector(searchedProductName)
        return Job()
    }
}
