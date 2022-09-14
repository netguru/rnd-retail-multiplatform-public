package com.netguru.commondomain.product

import app.cash.turbine.test
import com.netguru.commondomain.ar.ArService
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.ProductDetailsDataSource
import com.netguru.commondomain.data.ShopDataSource
import com.netguru.commondomain.product.model.*
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.shop.ShopServiceImplTest
import com.netguru.commondomain.shop.model.Price
import com.netguru.commondomain.shop.model.Product
import com.netguru.commondomain.userContent.UserContentDataSource
import com.netguru.commondomain.userContent.UserContentService
import com.netguru.commondomain.userSingleImage.UserSingleImageService
import io.mockk.coEvery
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.*
import kotlin.test.*

private const val BLACK_NAME = "Black"
private const val BLACK_COLOR = 0xFF000000
private const val WHITE_NAME = "White"
private const val WHITE_COLOR = 0xFFFFFFFF
private const val SIZE_NAME = "15x15x10"

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsServiceImplTest {

    private val productStore = Store(ProductDetailsState())
    private val productDetailsDataSource = mockk<ProductDetailsDataSource>()
    private val shopDataSource = mockk<ShopDataSource>()
    private val arService = mockk<ArService>()
    private val userContentService = mockk<UserContentService>()
    private val userContentDataSource = mockk<UserContentDataSource>()
    private val userSingleImageService = mockk<UserSingleImageService>()
    private val mainRouter = mockk<MainRouter>()

    private val configuration1 = Configuration(
        id = "1",
        fabricVariant = FabricVariant(BLACK_NAME, BLACK_COLOR),
        woodVariant = WoodVariant(BLACK_NAME, BLACK_COLOR),
        sizeVariant = SizeVariant(SIZE_NAME)
    )
    private val configuration2 = Configuration(
        id = "2",
        fabricVariant = FabricVariant(WHITE_NAME, WHITE_COLOR),
        woodVariant = WoodVariant(BLACK_NAME, BLACK_COLOR),
        sizeVariant = SizeVariant(SIZE_NAME)
    )
    private val productDetailsFromSource = ProductDetails(
        productId = "1",
        description = "Description",
        averageStars = 3.3f,
        numOfReviews = 22,
        defaultConfiguration = configuration1,
        possibleConfigurations = listOf(configuration1, configuration2),
        gallery = listOf(
            GalleryImage("imageUrl1", configuration1.id),
            GalleryImage("imageUrl2", configuration1.id),
            GalleryImage("imageUrl3", configuration2.id),
            GalleryImage("imageUrl4", configuration2.id)
        )
    )
    private val productFromSource = Product(
        id = "1",
        name = "Whinston",
        category = "Couch",
        price = Price(amount = 5f)
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
    fun `GIVEN productDetails from source WHEN load data THEN update state with loaded product and details`() =
        runTest {
            productStore.stream.test {
                coEvery {
                    productDetailsDataSource.getForProduct(any())
                } returns productDetailsFromSource
                coEvery {
                    shopDataSource.getProduct(any())
                } returns productFromSource
                coEvery {
                    userContentDataSource.getUserContentImages(any())
                } returns emptyList()

                val productDetailsService = createService()
                productDetailsService.loadData("1").join()

                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(ProductDetails(), productDetails)
                    assertEquals(Product(), product)
                }
                awaitItem().run {
                    assertEquals(Loading.InProgress, loading)
                    assertEquals(ProductDetails(), productDetails)
                    assertEquals(Product(), product)
                }
                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(productDetailsFromSource, productDetails)
                    assertEquals(productFromSource, product)
                }
            }
        }

    @Test
    fun `GIVEN productDetails from source not available WHEN load data THEN update state with error`() =
        runTest {
            productStore.stream.test {
                coEvery {
                    shopDataSource.getProduct(any())
                } returns productFromSource
                coEvery {
                    productDetailsDataSource.getForProduct("1")
                } throws Exception(ShopServiceImplTest.SERVER_ERROR_MESSAGE)
                coEvery {
                    userContentDataSource.getUserContentImages(any())
                } returns emptyList()

                val productDetailsService = createService()
                productDetailsService.loadData("1").join()

                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(ProductDetails(), productDetails)
                }
                awaitItem().run {
                    assertEquals(Loading.InProgress, loading)
                    assertEquals(ProductDetails(), productDetails)
                }
                awaitItem().run {
                    assertTrue { loading is Loading.Failed }
                    assertEquals(SERVER_ERROR_MESSAGE, (loading as Loading.Failed).error.message)
                    assertEquals(ProductDetails(), productDetails)
                }
            }
        }

    @Test
    fun `GIVEN loaded details WHEN go back THEN reset details and navigate back`() =
        runTest {
            justRun { mainRouter.back() }
            productStore.update {
                it.copy(
                    product = productFromSource,
                    productDetails = productDetailsFromSource
                )
            }

            productStore.stream.test {
                val productDetailsService = createService()
                productDetailsService.goBack().join()

                awaitItem().run {
                    assertEquals(productDetailsFromSource, productDetails)
                    assertEquals(productFromSource, product)
                }
                awaitItem().run {
                    assertEquals(ProductDetails(), productDetails)
                    assertEquals(Product(), product)
                }
            }
            verify { mainRouter.back() }
        }

    @Test
    fun `GIVEN initial state WHEN change increase and decrease selected amount THEN the state changes accordingly`() =
        runTest {
            productStore.stream.test {
                val service = createService()
                service.increaseSelectedAmount()
                service.decreaseSelectedAmount()

                awaitItem().run {
                    assertEquals(1, selectedAmount)
                }
                awaitItem().run {
                    assertEquals(2, selectedAmount)
                }
                awaitItem().run {
                    assertEquals(1, selectedAmount)
                }
            }
        }

    @Test
    fun `GIVEN loaded details WHEN reset configuration THEN selected configuration is the default one`() =
        runTest {
            productStore.update {
                it.copy(
                    productDetails = productDetailsFromSource,
                    selectedConfigurationIndex = 1
                )
            }
            val service = createService()

            productStore.stream.test {
                service.resetConfiguration().join()

                awaitItem().run {
                    assertEquals(1, selectedConfigurationIndex)
                }
                awaitItem().run {
                    assertEquals(
                        productDetailsFromSource.defaultConfiguration,
                        selectedConfiguration
                    )
                }
            }
        }

    @Test
    fun `GIVEN main router just runs WHEN show in ar THEN main router has run`() =
        runTest {
            coEvery {
                arService.loadAr(any())
            } returns Job()

            justRun { mainRouter.toProductAr() }

            val service = createService()
            service.showInAr().join()

            verify { mainRouter.toProductAr() }
        }

    @Test
    fun `GIVEN loaded details WHEN select configuration with white fabric THEN second configuration is selected`() =
        runTest {
            productStore.update {
                it.copy(
                    productDetails = productDetailsFromSource
                )
            }
            val service = createService()

            productStore.stream.test {
                service.selectConfiguration(
                    CustomizationRequest(VariantType.FABRIC, WHITE_NAME)
                )

                awaitItem().run {
                    assertEquals(configuration1, selectedConfiguration)
                }
                awaitItem().run {
                    assertEquals(configuration2, selectedConfiguration)
                }
            }
        }

    private fun TestScope.createService(): ProductDetailsService =
        ProductDetailsServiceImpl(
            productStore,
            productDetailsDataSource,
            shopDataSource,
            arService,
            userContentDataSource,
            userContentService,
            userSingleImageService,
            mainRouter,
            this
        )

    companion object {
        const val SERVER_ERROR_MESSAGE = "Server not available"
    }
}
