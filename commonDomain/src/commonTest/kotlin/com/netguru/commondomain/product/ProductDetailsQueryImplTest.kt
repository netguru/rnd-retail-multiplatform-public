package com.netguru.commondomain.product

import com.netguru.commondomain.base.Store
import com.netguru.commondomain.product.model.*
import com.netguru.commondomain.shop.model.Price
import com.netguru.commondomain.shop.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

private const val BLACK_NAME = "Black"
private const val BLACK_COLOR = 0xFF000000
private const val WHITE_NAME = "White"
private const val WHITE_COLOR = 0xFFFFFFFF
private const val SIZE_NAME = "15x15x10"

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailsQueryImplTest {
    private val productStore = Store(ProductDetailsState())

    private val blackFabricVariant = FabricVariant(BLACK_NAME, BLACK_COLOR)
    private val whiteFabricVariant = FabricVariant(WHITE_NAME, WHITE_COLOR)

    private val blackWoodVariant = WoodVariant(BLACK_NAME, BLACK_COLOR)
    private val normalSizeVariant = SizeVariant(SIZE_NAME)

    private val configuration1 = Configuration(
        id = "1",
        fabricVariant = blackFabricVariant,
        woodVariant = blackWoodVariant,
        sizeVariant = normalSizeVariant
    )
    private val configuration2 = Configuration(
        id = "2",
        fabricVariant = whiteFabricVariant,
        woodVariant = blackWoodVariant,
        sizeVariant = normalSizeVariant
    )
    private val details = ProductDetails(
        productId = "1",
        description = "Beautiful product",
        averageStars = 4.2f,
        numOfReviews = 32,
        defaultConfiguration = configuration1,
        possibleConfigurations = listOf(configuration1, configuration2),
        gallery = listOf(
            GalleryImage("imageUrl1", configuration1.id),
            GalleryImage("imageUrl2", configuration1.id),
            GalleryImage("imageUrl3", configuration2.id),
            GalleryImage("imageUrl4", configuration2.id)
        )
    )

    private val product = Product(
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
    fun `GIVEN initial state WHEN get product details THEN empty details are emitted `() = runTest {
        var productDetails: ProductInfo? = null
        val productQuery = createQuery()

        val job = productQuery.collectProductInfo { productDetails = it }
        advanceUntilIdle()
        job.cancel()

        assertEquals(ProductInfo(), productDetails!!)
    }

    @Test
    fun `GIVEN product and details WHEN get product info THEN emit the product info`() = runTest {
        productStore.update(
            ProductDetailsState(
                product = product,
                productDetails = details
            )
        )
        var collectedProductInfo = ProductInfo()

        val productDetailsQuery = createQuery()
        val job = productDetailsQuery.collectProductInfo { collectedProductInfo = it }
        advanceUntilIdle()
        job.cancel()

        val expectedProductInfo = ProductInfo(
            name = product.name,
            price = product.price,
            averageStars = details.averageStars,
            numOfReviews = details.numOfReviews,
            description = details.description
        )
        assertEquals(expectedProductInfo, collectedProductInfo)
    }

    @Test
    fun `GIVEN details WHEN get gallery THEN emit the gallery`() = runTest {
        productStore.update(
            ProductDetailsState(
                product = product,
                productDetails = details,
                selectedConfigurationIndex = 0
            )
        )
        var collectedGallery = emptyList<String>()

        val productDetailsQuery = createQuery()
        val job = productDetailsQuery.collectGallery { collectedGallery = it }
        advanceUntilIdle()
        job.cancel()

        val expectedGallery = details.gallery
            .filter { it.configurationId == configuration1.id }
            .map { it.imageUrl }
        assertEquals(expectedGallery, collectedGallery)
    }

    @Test
    fun `GIVEN details WHEN get customization info THEN emit the customization info`() = runTest {
        productStore.update {
            it.copy(productDetails = details)
        }
        var collectedCustomizationInfo = CustomizationInfo()

        val productDetailsQuery = createQuery()
        val job = productDetailsQuery.collectCustomizationInfo { collectedCustomizationInfo = it }
        advanceUntilIdle()
        job.cancel()

        val expectedCustomizationInfo = CustomizationInfo(
            availableFabrics = listOf(
                FabricVariantInfo(
                    colorName = blackFabricVariant.colorName,
                    colorCode = blackFabricVariant.colorCode,
                    selectable = true
                ),
                FabricVariantInfo(
                    colorName = whiteFabricVariant.colorName,
                    colorCode = whiteFabricVariant.colorCode,
                    selectable = true
                )
            ),
            availableWoods = listOf(
                WoodVariantInfo(
                    colorName = blackWoodVariant.colorName,
                    colorCode = blackWoodVariant.colorCode,
                    selectable = true
                )
            ),
            availableSizes = listOf(
                SizeVariantInfo(normalSizeVariant.sizeName, true)
            ),
            selectedFabric = blackFabricVariant,
            selectedWoodName = blackWoodVariant.colorName,
            selectedSizeName = normalSizeVariant.sizeName
        )
        assertEquals(expectedCustomizationInfo, collectedCustomizationInfo)
    }

    @Test
    fun `GIVEN product WHEN get shopping cart info THEN emit shopping cart info`() = runTest {
        val selectedAmount = 2
        productStore.update {
            it.copy(
                product = product,
                selectedAmount = selectedAmount
            )
        }
        var collectedShoppingCartInfo = ShoppingCartInfo()

        val productDetailsQuery = createQuery()
        val job = productDetailsQuery.collectShoppingCartInfo { collectedShoppingCartInfo = it }
        advanceUntilIdle()
        job.cancel()

        val expectedShoppingCartInfo = ShoppingCartInfo(
            numOfItemsInCart = 2,
            totalPrice = Price(product.price.currency, product.price.amount * selectedAmount)
        )
        assertEquals(expectedShoppingCartInfo, collectedShoppingCartInfo)
    }

    private fun TestScope.createQuery(): ProductDetailsQuery =
        ProductDetailsQueryImpl(productStore, this)
}
