//
//  DetailsViewModel.swift
//  R&D KMM Retail
//

import Foundation
import common

/// A view model to be used for the product details screen.
class DetailsViewModel: ObservableObject {

    // MARK: - Public Properties

    /// Main source of the product basic information.
    @Published var productInfo = ProductInfo.companion.empty

    /// Main source of the product customization options.
    @Published var customizationInfo = CustomizationInfo.companion.empty

    /// Main source of the shopping cart state.
    @Published var shoppingCartInfo = ShoppingCartInfo.companion.empty

    /// Array of strings representing image URLs
    @Published var gallery = [String]()

    /// Array of strings representing image URLs
    @Published var userContentImages = [String]()

    /// Boolaen value for showing details bottom customization modal sheet.
    @Published var detailsModalShowing = false

    // MARK: - Private Properties

    private let productDetailsQuery: ProductDetailsQuery

    private let productDetailsService: ProductDetailsService

    private let pool = CommonJobsPool()

    // MARK: - Initializers

    /// A default DetailsViewModel initializer.
    ///
    /// - Parameters:
    ///   - productDetailsQuery: Observes the state and provides data prepared for displaying. Read business logic.
    ///   - productDetailsService: Handles user intents and updates the state accordingly. Write business logic.
    init(
        productDetailsQuery: ProductDetailsQuery = Provider.shared.productDetailsQuery,
        productDetailsService: ProductDetailsService = Provider.shared.productDetailsService
    ) {
        self.productDetailsQuery = productDetailsQuery
        self.productDetailsService = productDetailsService

        pool.run(collect: productDetailsQuery.collectGallery) { [weak self] gallery in
            guard let self = self else { return }
            self.gallery = gallery
        }

        pool.run(collect: productDetailsQuery.collectProductInfo) { [weak self] productInfo in
            guard let self = self else { return }
            self.productInfo = productInfo
        }

        pool.run(collect: productDetailsQuery.collectCustomizationInfo) { [weak self] customizationInfo in
            guard let self = self else { return }
            self.customizationInfo = customizationInfo
        }

        pool.run(collect: productDetailsQuery.collectShoppingCartInfo) { [weak self] shoppingCartInfo in
            guard let self = self else { return }
            self.shoppingCartInfo = shoppingCartInfo
        }

        pool.run(collect: productDetailsQuery.collectUserContentImages) { [weak self] userContentImages in
            guard let self = self else { return }
            self.userContentImages = userContentImages
        }
    }

    // MARK: - Public Methods

    func handleBack() {
        productDetailsService.goBack()
    }

    func handleFabricCustomize(variantName: String) {
        let customizationRequest = CustomizationRequest(
            variantType: .fabric,
            variantName: variantName
        )
        productDetailsService.selectConfiguration(request: customizationRequest)
        HapticFeedback.vibrate(with: .selection)
    }

    func handleWoodCustomize(variantName: String) {
        let customizationRequest = CustomizationRequest(
            variantType: .wood,
            variantName: variantName
        )
        productDetailsService.selectConfiguration(request: customizationRequest)
        HapticFeedback.vibrate(with: .selection)
    }

    func handleSizeCustomize(variantName: String) {
        let customizationRequest = CustomizationRequest(
            variantType: .size,
            variantName: variantName
        )
        productDetailsService.selectConfiguration(request: customizationRequest)
        HapticFeedback.vibrate(with: .selection)
    }

    func handleReset() {
        productDetailsService.resetConfiguration()
        HapticFeedback.vibrate(with: .selection)
    }

    func handlePlus() {
        productDetailsService.increaseSelectedAmount()
        HapticFeedback.vibrate(with: .selection)
    }

    func handleMinus() {
        productDetailsService.decreaseSelectedAmount()
        HapticFeedback.vibrate(with: .selection)
    }

    func handleARViewClick() {
        productDetailsService.showInAr()
        HapticFeedback.vibrate(with: .selection)
    }

    func handleShowAllClick() {
        productDetailsService.openUserContent()
        HapticFeedback.vibrate(with: .selection)
    }

    func handleShowFullscreenImage(imageUrl: String) {
        productDetailsService.showFullscreenImage(imageUrl: imageUrl)
        HapticFeedback.vibrate(with: .selection)
    }

    func formattedTotalPrice() -> String {
        let price = shoppingCartInfo.totalPrice
        let formatter = NumberFormatter()
        formatter.numberStyle = .decimal
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        guard let formattedPrice = formatter.string(from: NSNumber(value: price.amount)) else { return "" }
        return price.currency + formattedPrice
    }

    deinit {
        pool.cancelAll()
    }
}
