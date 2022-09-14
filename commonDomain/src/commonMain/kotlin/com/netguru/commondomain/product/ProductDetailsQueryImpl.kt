package com.netguru.commondomain.product

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.base.asQuery
import com.netguru.commondomain.product.ProductDetailsConsts.CART_MAX_AMOUNT
import com.netguru.commondomain.product.ProductDetailsConsts.CART_MIN_AMOUNT
import com.netguru.commondomain.product.model.*
import com.netguru.commondomain.shop.model.Price
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class ProductDetailsQueryImpl(
    private val productStore: Store<ProductDetailsState>,
    private val scope: CoroutineScope
) : ProductDetailsQuery, CoroutineScope by scope {

    override fun collectGallery(collector: (List<String>) -> Unit): Job = launch {
        productStore.stream
            .map { state ->
                state.productDetails
                    .gallery
                    .filter { it.configurationId == state.selectedConfiguration.id }
                    .map { it.imageUrl }
            }
            .asQuery(collector)
    }

    override fun collectProductInfo(collector: (ProductInfo) -> Unit): Job = launch {
        productStore.stream
            .map { state ->
                val product = state.product
                val details = state.productDetails
                ProductInfo(
                    name = product.name,
                    price = product.price,
                    description = details.description,
                    averageStars = details.averageStars,
                    numOfReviews = details.numOfReviews
                )
            }
            .asQuery(collector)
    }

    override fun collectCustomizationInfo(collector: (CustomizationInfo) -> Unit): Job = launch {
        productStore.stream
            .map { state ->
                val selectedFabric = state.selectedConfiguration.fabricVariant
                val selectedWood = state.selectedConfiguration.woodVariant
                val selectedSize = state.selectedConfiguration.sizeVariant
                CustomizationInfo(
                    availableFabrics = state.getFabricVariantInfos(),
                    selectedFabric = selectedFabric,
                    availableWoods = state.getWoodVariantInfos(),
                    selectedWoodName = selectedWood.colorName,
                    availableSizes = state.getSizeVariantInfos(),
                    selectedSizeName = selectedSize.sizeName,
                    numOfSelectedCustomizations = state.countSelectedCustomizations()
                )
            }
            .asQuery(collector)
    }

    override fun collectUserContentImages(collector: (List<String>) -> Unit): Job = launch {
        productStore.stream
            .map { state ->
                state.productDetails.userContentImages
                    .map { it.imageUrl }
            }
            .asQuery(collector)
    }

    private fun ProductDetailsState.getFabricVariantInfos(): List<FabricVariantInfo> {
        val possibleConfigs = productDetails.possibleConfigurations
        val selectableFabrics = possibleConfigs
            .filter { selectedConfiguration.sizeVariant == it.sizeVariant }
            .filter { selectedConfiguration.woodVariant == it.woodVariant }
            .map { it.fabricVariant }
            .distinct()
        val allFabrics = possibleConfigs
            .map { it.fabricVariant }
            .distinct()
        return allFabrics.map {
            FabricVariantInfo(
                colorName = it.colorName,
                colorCode = it.colorCode,
                selectable = selectableFabrics.contains(it)
            )
        }
    }

    private fun ProductDetailsState.getWoodVariantInfos(): List<WoodVariantInfo> {
        val possibleConfigs = productDetails.possibleConfigurations
        val selectableWoods = possibleConfigs
            .filter { selectedConfiguration.sizeVariant == it.sizeVariant }
            .filter { selectedConfiguration.fabricVariant == it.fabricVariant }
            .map { it.woodVariant }
            .distinct()
        val allWoods = possibleConfigs
            .map { it.woodVariant }
            .distinct()
        return allWoods.map {
            WoodVariantInfo(
                colorName = it.colorName,
                colorCode = it.colorCode,
                selectable = selectableWoods.contains(it)
            )
        }
    }

    private fun ProductDetailsState.getSizeVariantInfos(): List<SizeVariantInfo> {
        val possibleConfigs = productDetails.possibleConfigurations
        val selectableSizes = possibleConfigs
            .filter { selectedConfiguration.fabricVariant == it.fabricVariant }
            .filter { selectedConfiguration.woodVariant == it.woodVariant }
            .map { it.sizeVariant }
            .distinct()
        val allFabrics = possibleConfigs
            .map { it.sizeVariant }
            .distinct()
        return allFabrics.map {
            SizeVariantInfo(
                sizeName = it.sizeName,
                selectable = selectableSizes.contains(it)
            )
        }
    }

    override fun collectShoppingCartInfo(collector: (ShoppingCartInfo) -> Unit): Job = launch {
        productStore.stream
            .map { state ->
                val singleProductPrice = state.product.price
                ShoppingCartInfo(
                    numOfItemsInCart = state.selectedAmount,
                    totalPrice = Price(
                        currency = singleProductPrice.currency,
                        amount = singleProductPrice.amount * state.selectedAmount
                    ),
                    canIncrement = state.selectedAmount < CART_MAX_AMOUNT,
                    canDecrement = state.selectedAmount > CART_MIN_AMOUNT
                )
            }
            .asQuery(collector)
    }

    override fun collectLoading(collector: (Loading) -> Unit): Job = launch {
        productStore
            .stream
            .map { state -> state.loading }
            .asQuery(collector)
    }

    private fun ProductDetailsState.countSelectedCustomizations(): Int {
        val defaultConfiguration = productDetails.defaultConfiguration
        var customizationsApplied = 0
        if (selectedConfiguration.sizeVariant != defaultConfiguration.sizeVariant) {
            customizationsApplied++
        }
        if (selectedConfiguration.fabricVariant != defaultConfiguration.fabricVariant) {
            customizationsApplied++
        }
        if (selectedConfiguration.woodVariant != defaultConfiguration.woodVariant) {
            customizationsApplied++
        }
        return customizationsApplied
    }
}
