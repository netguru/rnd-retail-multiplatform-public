package com.netguru.commondomain.shop

import kotlinx.coroutines.Job

interface ShopService {

    fun loadData(): Job

    fun openProductDetails(productId: String): Job

    fun searchProduct(productName: String): Job
}
