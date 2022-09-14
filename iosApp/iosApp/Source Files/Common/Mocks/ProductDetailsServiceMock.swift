//
//  ProductDetailsServiceMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class ProductDetailsServiceMock: ProductDetailsService {
    func decreaseSelectedAmount() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func increaseSelectedAmount() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func loadData(productId: String) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func resetConfiguration() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func selectConfiguration(request: CustomizationRequest) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func goBack() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func showInAr() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func openUserContent() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }

    func showFullscreenImage(imageUrl: String) -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
