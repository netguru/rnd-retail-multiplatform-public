//
//  ShopViewModel.swift
//  R&D KMM Retail
//

import UIKit
import Foundation
import common

class ShopViewModel: ObservableObject {

    @Published var searchText: String = ""

    @Published var recommendedProducts = [RecommendedProduct]()

    @Published var recentlyViewedProducts = [RecentlyViewedProduct]()

    private let shopQuery: ShopQuery
    private let shopService: ShopService
    private let pool = CommonJobsPool()

    init(
        shopQuery: ShopQuery = Provider.shared.shopQuery,
        shopService: ShopService = Provider.shared.shopService
    ) {
        self.shopQuery = shopQuery
        self.shopService = shopService
        pool.run(collect: shopQuery.collectRecommendedProducts) { [weak self] products in
            guard let self = self else { return }
            self.recommendedProducts = products
        }
        pool.run(collect: shopQuery.collectRecentlyViewedProducts) { [weak self] products in
            guard let self = self else { return }
            self.recentlyViewedProducts = products
        }
        pool.run(collect: shopQuery.collectSearchedProductName) { [weak self] productName in
            guard let self = self else { return }
            self.searchText = productName
        }
        shopService.loadData()
    }

    deinit {
        pool.cancelAll()
    }

    func handleSearchProduct(productName: String) {
        shopService.searchProduct(productName: productName)
        HapticFeedback.vibrate(with: .selection)
    }

    func handleRecommendedProductClick(product: RecommendedProduct) {
        shopService.openProductDetails(productId: product.id)
        HapticFeedback.vibrate(with: .selection)
    }

    func handleRecentlyViewedProductClick(product: RecentlyViewedProduct) {
        shopService.openProductDetails(productId: product.id)
        HapticFeedback.vibrate(with: .selection)
    }
}
