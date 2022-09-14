package com.netguru.commondomain.product

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.product.model.CustomizationInfo
import com.netguru.commondomain.product.model.ProductInfo
import com.netguru.commondomain.product.model.ShoppingCartInfo
import kotlinx.coroutines.Job

interface ProductDetailsQuery {
    fun collectGallery(collector: (List<String>) -> Unit): Job
    fun collectProductInfo(collector: (ProductInfo) -> Unit): Job
    fun collectCustomizationInfo(collector: (CustomizationInfo) -> Unit): Job
    fun collectUserContentImages(collector: (List<String>) -> Unit): Job
    fun collectShoppingCartInfo(collector: (ShoppingCartInfo) -> Unit): Job
    fun collectLoading(collector: (Loading) -> Unit): Job
}
