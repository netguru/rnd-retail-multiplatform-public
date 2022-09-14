//
//  ShopServiceMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class ShopServiceMock: ShopService {

    func loadData() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func openProductDetails(productId: String) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func searchProduct(productName: String) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
