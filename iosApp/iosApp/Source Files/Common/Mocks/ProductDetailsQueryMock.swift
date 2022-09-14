//
//  ProductDetailsQueryMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class ProductDetailsQueryMock: ProductDetailsQuery {
    func collectCustomizationInfo(collector: @escaping (CustomizationInfo) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectGallery(collector: @escaping ([String]) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectLoading(collector: @escaping (Loading) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectProductInfo(collector: @escaping (ProductInfo) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectShoppingCartInfo(collector: @escaping (ShoppingCartInfo) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func collectUserContentImages(collector: @escaping ([String]) -> Void) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
