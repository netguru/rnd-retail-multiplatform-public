package com.netguru.commondomain.shop

import com.netguru.commondomain.base.Store
import com.netguru.commondomain.shop.model.Highlight
import com.netguru.commondomain.shop.model.Product
import com.netguru.commondomain.shop.model.RecentlyViewedProduct
import com.netguru.commondomain.shop.model.RecommendedProduct
import com.soywiz.klock.DateTime
import com.soywiz.klock.hours
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ShopQueryImplTest {
    private val shopStore = Store(ShopState())

    private val recommendedProduct = Product(
        id = "1",
        name = "Product 1",
        highlights = setOf(Highlight.RECOMMENDED)
    )
    private val recentlyViewedProduct = Product(
        id = "2",
        name = "Product 2",
        lastViewed = DateTime.now() + 2.hours
    )
    private val newRecommendedProduct = Product(
        id = "3",
        name = "Product 3",
        highlights = setOf(Highlight.NEW, Highlight.RECOMMENDED)
    )
    private val newRecentlyViewedProduct = Product(
        id = "4",
        name = "Product 4",
        highlights = setOf(Highlight.NEW),
        lastViewed = DateTime.now() + 1.hours
    )

    @BeforeTest
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN initial state WHEN get recommended products THEN empty list is emitted`() = runTest {
        var products: List<RecommendedProduct>? = null

        val shopQuery = createQuery()
        val job = shopQuery.collectRecommendedProducts { products = it }
        advanceUntilIdle()
        job.cancel()

        assertTrue(products!!.isEmpty())
    }

    @Test
    fun `GIVEN initial state WHEN get recently viewed products THEN empty list is emitted`() =
        runTest {
            var products: List<RecentlyViewedProduct>? = null

            val shopQuery = createQuery()
            val job = shopQuery.collectRecentlyViewedProducts { products = it }
            advanceUntilIdle()
            job.cancel()

            assertTrue(products!!.isEmpty())
        }

    @Test
    fun `GIVEN initial state WHEN get searched product name THEN emit empty string`() = runTest {
        var productName: String? = null

        val shopQuery = createQuery()
        val job = shopQuery.collectSearchedProductName { productName = it }
        advanceUntilIdle()
        job.cancel()

        assertEquals("", productName)
    }

    @Test
    fun `GIVEN loaded products WHEN get recommended products THEN emit only products highlighted as recommended`() =
        runTest {
            val loadedProducts = listOf(
                recommendedProduct,
                recentlyViewedProduct,
                newRecommendedProduct
            )
            shopStore.update(ShopState(products = loadedProducts))
            var recommendedProducts = emptyList<RecommendedProduct>()

            val shopQuery = createQuery()
            val job = shopQuery.collectRecommendedProducts { recommendedProducts = it }
            advanceUntilIdle()
            job.cancel()

            assertEquals(2, recommendedProducts.size)
            recommendedProducts[0].run {
                assertEquals(recommendedProduct.id, id)
                assertEquals(recommendedProduct.name, name)
                assertFalse(isNew)
            }
            recommendedProducts[1].run {
                assertEquals(newRecommendedProduct.id, id)
                assertEquals(newRecommendedProduct.name, name)
                assertTrue(isNew)
            }
        }

    @Test
    fun `GIVEN loaded products WHEN get recently viewed products THEN emit only products highlighted as recently viewed`() =
        runTest {
            val loadedProducts = listOf(
                recentlyViewedProduct,
                recommendedProduct,
                newRecentlyViewedProduct
            )
            shopStore.update(ShopState(products = loadedProducts))
            var recentlyViewedProducts = emptyList<RecentlyViewedProduct>()

            val shopQuery = createQuery()
            val job = shopQuery.collectRecentlyViewedProducts { recentlyViewedProducts = it }
            advanceUntilIdle()
            job.cancel()

            assertEquals(2, recentlyViewedProducts.size)
            recentlyViewedProducts[0].run {
                assertEquals(recentlyViewedProduct.id, id)
                assertEquals(recentlyViewedProduct.name, name)
            }
            recentlyViewedProducts[1].run {
                assertEquals(newRecentlyViewedProduct.id, id)
                assertEquals(newRecentlyViewedProduct.name, name)
            }
        }

    private fun TestScope.createQuery(): ShopQuery =
        ShopQueryImpl(shopStore, this)
}
