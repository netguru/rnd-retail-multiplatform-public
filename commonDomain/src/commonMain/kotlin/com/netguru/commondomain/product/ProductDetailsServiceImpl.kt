package com.netguru.commondomain.product

import com.netguru.commondomain.ar.ArService
import com.netguru.commondomain.ar.model.ArModelConfiguration
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.ProductDetailsDataSource
import com.netguru.commondomain.data.ShopDataSource
import com.netguru.commondomain.product.ProductDetailsConsts.CART_MAX_AMOUNT
import com.netguru.commondomain.product.ProductDetailsConsts.CART_MIN_AMOUNT
import com.netguru.commondomain.product.model.CustomizationRequest
import com.netguru.commondomain.product.model.VariantType
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.userContent.UserContentDataSource
import com.netguru.commondomain.userContent.UserContentService
import com.netguru.commondomain.userContent.model.UserContentRequest
import com.netguru.commondomain.userSingleImage.UserSingleImageService
import com.netguru.commondomain.userSingleImage.model.SingleImageRequest
import kotlinx.coroutines.*

@Suppress("LongParameterList")
internal class ProductDetailsServiceImpl(
    private val productDetailsStore: Store<ProductDetailsState>,
    private val productDetailsDataSource: ProductDetailsDataSource,
    private val shopDataSource: ShopDataSource,
    private val arService: ArService,
    private val userContentDataSource: UserContentDataSource,
    private val userContentService: UserContentService,
    private val userSingleImageService: UserSingleImageService,
    private val mainRouter: MainRouter,
    private val scope: CoroutineScope
) : ProductDetailsService, CoroutineScope by scope {

    override fun loadData(productId: String) = launch {
        supervisorScope {
            productDetailsStore.update {
                it.copy(loading = Loading.InProgress)
            }
            val product = async { shopDataSource.getProduct(productId) }
            val userContentImages = async {
                userContentDataSource.getUserContentImages(productId).take(n = 5)
            }
            val details = async { productDetailsDataSource.getForProduct(productId) }
            try {
                val newState = productDetailsStore.state.copy(
                    product = product.await(),
                    productDetails = details.await().copy(
                        userContentImages = userContentImages.await()
                    ),
                    loading = Loading.Idle
                )
                productDetailsStore.update { newState }
            } catch (error: Exception) {
                productDetailsStore.update {
                    it.copy(loading = Loading.Failed(error))
                }
            }
        }
    }

    override fun goBack(): Job = launch {
        withContext(Dispatchers.Main) {
            mainRouter.back()
        }
        productDetailsStore.reset(delayReset = true)
    }

    override fun showInAr(): Job = launch {
        val arModelConfiguration = ArModelConfiguration(
            productDetailsStore.state.product.id,
            productDetailsStore.state.selectedConfiguration.id
        )
        arService.loadAr(arModelConfiguration)
        withContext(Dispatchers.Main) {
            mainRouter.toProductAr()
        }
    }

    override fun decreaseSelectedAmount(): Job = launch {
        productDetailsStore.update {
            val newAmount = if (it.selectedAmount > CART_MIN_AMOUNT) {
                it.selectedAmount - 1
            } else {
                it.selectedAmount
            }
            it.copy(selectedAmount = newAmount)
        }
    }

    override fun increaseSelectedAmount(): Job = launch {
        productDetailsStore.update {
            val newAmount = if (it.selectedAmount < CART_MAX_AMOUNT) {
                it.selectedAmount + 1
            } else {
                it.selectedAmount
            }
            it.copy(selectedAmount = newAmount)
        }
    }

    override fun selectConfiguration(request: CustomizationRequest): Job = launch {
        productDetailsStore.update {
            val currentConfiguration = it.selectedConfiguration
            val configurations = it.productDetails.possibleConfigurations

            val newFabricName = if (request.variantType == VariantType.FABRIC) {
                request.variantName
            } else {
                currentConfiguration.fabricVariant.colorName
            }

            val newWoodName = if (request.variantType == VariantType.WOOD) {
                request.variantName
            } else {
                currentConfiguration.woodVariant.colorName
            }

            val newSizeName = if (request.variantType == VariantType.SIZE) {
                request.variantName
            } else {
                currentConfiguration.sizeVariant.sizeName
            }

            val newConfiguration = configurations
                .firstOrNull { config ->
                    config.fabricVariant.colorName == newFabricName &&
                        config.woodVariant.colorName == newWoodName &&
                        config.sizeVariant.sizeName == newSizeName
                }

            val newConfigurationIndex = if (configurations.contains(newConfiguration)) {
                configurations.indexOf(newConfiguration)
            } else {
                configurations.indexOf(currentConfiguration)
            }
            it.copy(selectedConfigurationIndex = newConfigurationIndex)
        }
    }

    override fun resetConfiguration(): Job = launch {
        productDetailsStore.update {
            val configurations = it.productDetails.possibleConfigurations
            val defaultConfig = it.productDetails.defaultConfiguration
            val defaultConfigIndex = configurations.indexOf(defaultConfig)
            it.copy(selectedConfigurationIndex = defaultConfigIndex)
        }
    }

    override fun openUserContent(): Job = launch {
        val userContentRequest = UserContentRequest(
            productName = productDetailsStore.state.product.name,
            productId = productDetailsStore.state.product.id
        )
        userContentService.loadUserContent(userContentRequest)
        withContext(Dispatchers.Main) {
            mainRouter.toUserContent()
        }
    }

    override fun showFullscreenImage(imageUrl: String): Job = launch {
        val singleImageRequest = SingleImageRequest(
            productName = productDetailsStore.state.product.name,
            imageUrl = imageUrl
        )
        userSingleImageService.loadUserContent(singleImageRequest)
        withContext(Dispatchers.Main) {
            mainRouter.toUserSingleImage()
        }
    }
}
