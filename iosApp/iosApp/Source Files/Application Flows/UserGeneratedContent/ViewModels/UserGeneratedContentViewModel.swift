//
//  UserGeneratedContentViewModel.swift
//  R&D KMM Retail
//

import Foundation
import common

/// A view model to be used for the displaying gallery of User Generated images.
class UserGeneratedContentViewModel: ObservableObject {

    // MARK: - Public Properties

    @Published var productName: String = ""

    @Published var imageUrls: [String] = [String]()

    // MARK: - Private Properties

    private let userContentQuery: UserContentQuery

    private let userContentService: UserContentService

    private let pool = CommonJobsPool()

    // MARK: - Initializers

    /// A default DetailsViewModel initializer.
    ///
    /// - Parameters:
    ///   - userContentQuery: Observes the state and provides data prepared for displaying. Read business logic.
    ///   - userContentService: Handles user intents and updates the state accordingly. Write business logic.
    init(
        userContentQuery: UserContentQuery = Provider.shared.userContentQuery,
        userContentService: UserContentService = Provider.shared.userContentService
    ) {
        self.userContentQuery = userContentQuery
        self.userContentService = userContentService

        pool.run(collect: userContentQuery.collectUserContentImages) { [weak self] imageUrls in
            guard let self = self else { return }
            self.imageUrls = imageUrls
        }

        pool.run(collect: userContentQuery.collectUserContentTitle) { [weak self] productName in
            guard let self = self else { return }
            self.productName = productName
        }
    }

    // MARK: - Public Methods

    func handleBack() {
        userContentService.goBack()
    }

    func handleShowFullscreenImage(imageUrl: String) {
        userContentService.showImageInFullscreen(imageUrl: imageUrl)
    }

    deinit {
        pool.cancelAll()
    }
}
