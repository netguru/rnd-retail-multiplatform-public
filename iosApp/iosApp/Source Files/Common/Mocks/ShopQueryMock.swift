//
//  ShopQueryMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class ShopQueryMock: ShopQuery {

    func collectRecommendedProducts(collector: @escaping ([RecommendedProduct]) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectRecentlyViewedProducts(collector: @escaping ([RecentlyViewedProduct]) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectSearchedProductName(collector: @escaping (String) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
