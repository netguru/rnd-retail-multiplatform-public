//
//  UserGeneratedSingleImageViewModel.swift
//  R&D KMM Retail
//

import Foundation
import common

/// A view model to be used for the displaying single image from User Generated content gallery.
class UserGeneratedSingleImageViewModel: ObservableObject {

    // MARK: - Public Properties

    @Published var productName: String = ""

    @Published var imageUrl: String = ""

    // MARK: - Private Properties

    private let userSingleImageQuery: UserSingleImageQuery

    private let userSingleImageService: UserSingleImageService

    private let pool = CommonJobsPool()

    // MARK: - Initializers

    /// A default DetailsViewModel initializer.
    ///
    /// - Parameters:
    ///   - userSingleImageQuery: Observes the state and provides data prepared for displaying. Read business logic.
    ///   - userSingleImageService: Handles user intents and updates the state accordingly. Write business logic.
    init(
        userSingleImageQuery: UserSingleImageQuery = Provider.shared.userSingleImageQuery,
        userSingleImageService: UserSingleImageService = Provider.shared.userSingleImageService
    ) {
        self.userSingleImageQuery = userSingleImageQuery
        self.userSingleImageService = userSingleImageService

        pool.run(collect: userSingleImageQuery.collectImageUrl) { [weak self] imageUrl in
            guard let self = self else { return }
            self.imageUrl = imageUrl
        }

        pool.run(collect: userSingleImageQuery.collectImageTitle) { [weak self] productName in
            guard let self = self else { return }
            self.productName = productName
        }
    }

    // MARK: - Public Methods\\

    func handleBack() {
        userSingleImageService.goBack()
    }

    deinit {
        pool.cancelAll()
    }
}
