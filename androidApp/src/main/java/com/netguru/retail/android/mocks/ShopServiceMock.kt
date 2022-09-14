package com.netguru.retail.android.mocks

import com.netguru.commondomain.shop.ShopService
import kotlinx.coroutines.Job

class ShopServiceMock : ShopService {
    override fun loadData(): Job = Job()

    override fun openProductDetails(productId: String): Job = Job()

    override fun searchProduct(productName: String): Job = Job()
}
