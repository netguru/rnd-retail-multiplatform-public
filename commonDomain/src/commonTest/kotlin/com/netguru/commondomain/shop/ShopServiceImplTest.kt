package com.netguru.commondomain.shop

import app.cash.turbine.test
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.ShopDataSource
import com.netguru.commondomain.product.ProductDetailsService
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.shop.model.Product
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ShopServiceImplTest {

    private val shopStore = Store(ShopState())
    private val shopDataSource = mockk<ShopDataSource>()
    private val mainRouter = mockk<MainRouter>()
    private val productDetailsService = mockk<ProductDetailsService>()

    private val product1 = Product(id = "1", name = "Product 1")
    private val product2 = Product(id = "2", name = "Product 2")
    private val productsFromSource = listOf(product1, product2)

    @BeforeTest
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN products from source WHEN load data THEN update state with loaded products`() =
        runTest {
            shopStore.stream.test {
                coEvery { shopDataSource.getProducts() } returns productsFromSource

                val shopService = createService()
                shopService.loadData().join()

                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(emptyList(), products)
                }
                awaitItem().run {
                    assertEquals(Loading.InProgress, loading)
                    assertEquals(emptyList(), products)
                }
                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(productsFromSource, products)
                }
            }
        }

    @Test
    fun `GIVEN products from source not available WHEN load data THEN update state with error`() =
        runTest {
            shopStore.stream.test {
                coEvery { shopDataSource.getProducts() } throws Exception(SERVER_ERROR_MESSAGE)

                val shopService = createService()
                shopService.loadData().join()

                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(emptyList(), products)
                }
                awaitItem().run {
                    assertEquals(Loading.InProgress, loading)
                    assertEquals(emptyList(), products)
                }
                awaitItem().run {
                    assertTrue { loading is Loading.Failed }
                    assertEquals(SERVER_ERROR_MESSAGE, (loading as Loading.Failed).error.message)
                    assertEquals(emptyList(), products)
                }
            }
        }

    @Test
    fun `GIVEN loaded products WHEN open product details THEN load details and navigate to product details`() =
        runTest {
            every { productDetailsService.loadData(any()) } returns launch {}
            justRun { mainRouter.toProductDetails() }
            shopStore.update(ShopState(products = productsFromSource))

            val shopService = createService()
            shopService.openProductDetails("1").join()

            verifyAll {
                productDetailsService.loadData("1")
                mainRouter.toProductDetails()
            }
        }

    @Test
    fun `GIVEN loaded products WHEN search product THEN searched product name is updated`() =
        runTest {
            shopStore.update(ShopState(products = productsFromSource))

            val shopService = createService()
            shopService.searchProduct(product1.name).join()

            shopStore.stream.test {
                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(productsFromSource, products)
                    assertEquals(product1.name, searchedProductName)
                }
            }
        }

    private fun TestScope.createService(): ShopService =
        ShopServiceImpl(
            shopStore,
            shopDataSource,
            mainRouter,
            productDetailsService,
            this
        )

    companion object {
        const val SERVER_ERROR_MESSAGE = "Server not available"
    }
}
