package com.netguru.commondomain.shop

import com.netguru.commondomain.base.Store
import com.netguru.commondomain.base.asQuery
import com.netguru.commondomain.shop.model.Highlight
import com.netguru.commondomain.shop.model.RecentlyViewedProduct
import com.netguru.commondomain.shop.model.RecommendedProduct
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.map

private const val RECENTLY_VIEWED_LIMIT = 5

internal class ShopQueryImpl(
    private val shopStore: Store<ShopState>,
    private val scope: CoroutineScope
) : ShopQuery, CoroutineScope by scope {

    override fun collectRecommendedProducts(
        collector: (List<RecommendedProduct>) -> Unit
    ) = launch {
        shopStore
            .stream
            .map { state ->
                state
                    .products
                    .filter { it.isMatching(state.searchedProductName) }
                    .filter { it.isHighlighted(Highlight.RECOMMENDED) }
                    .map { RecommendedProduct(it) }
            }
            .asQuery(collector)
    }

    override fun collectRecentlyViewedProducts(
        collector: (List<RecentlyViewedProduct>) -> Unit
    ) = launch {
        shopStore
            .stream
            .map { state ->
                state.products
                    .asSequence()
                    .filter { it.isMatching(state.searchedProductName) }
                    .filter { it.lastViewed != null }
                    .sortedByDescending { it.lastViewed }
                    .take(RECENTLY_VIEWED_LIMIT)
                    .map { RecentlyViewedProduct(it) }
                    .toList()
            }
            .asQuery(collector)
    }

    override fun collectSearchedProductName(collector: (String) -> Unit) = launch {
        shopStore
            .stream
            .map { it.searchedProductName }
            .asQuery(collector)
    }
}
