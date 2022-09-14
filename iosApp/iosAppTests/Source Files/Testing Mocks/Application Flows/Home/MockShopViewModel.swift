//
//  MockShopViewModel.swift
//  R&D KMM Retail
//

import common
import Foundation

@testable import Retail

final class MockShopViewModel: ShopViewModel {
    func mockBothCollections() {
        recommendedProducts = [
            RecommendedProduct(
                id: "1",
                name: "Twist Coffe Table",
                price: Price(currency: "$", amount: 103.0),
                thumbnailUrl: MockImages.mockProductGalleryUrls[0],
                isNew: true
            ),
            RecommendedProduct(
                id: "1",
                name: "Simple Chair",
                price: Price(currency: "$", amount: 58.0),
                thumbnailUrl: MockImages.mockProductGalleryUrls[1],
                isNew: false
            )
        ]
        recentlyViewedProducts = [
            RecentlyViewedProduct(
                id: "1",
                name: "Twist Coffe Table",
                category: "Tables",
                price: Price(currency: "$", amount: 103.0),
                thumbnailUrl: MockImages.mockProductGalleryUrls[2]
            ),
            RecentlyViewedProduct(
                id: "2",
                name: "Simple Chair",
                category: "Chairs",
                price: Price(currency: "$", amount: 58.0),
                thumbnailUrl: MockImages.mockProductGalleryUrls[0]
            )
        ]
    }

    func mockBothEmptyCollections() {
        recommendedProducts = []
        recentlyViewedProducts = []
    }

    func mockOneRecommendedFurniture() {
        recommendedProducts = [
            RecommendedProduct(
                id: "1",
                name: "Twist Coffe Table",
                price: Price(currency: "$", amount: 103.0),
                thumbnailUrl: MockImages.mockProductGalleryUrls[0],
                isNew: true
            )
        ]
    }

    func mockOneRecentlyViewedFurniture() {
        recentlyViewedProducts = [
            RecentlyViewedProduct(
                id: "1",
                name: "Twist Coffe Table",
                category: "Tables",
                price: Price(currency: "$", amount: 103.0),
                thumbnailUrl: MockImages.mockProductGalleryUrls[1]
            )
        ]
    }
}
