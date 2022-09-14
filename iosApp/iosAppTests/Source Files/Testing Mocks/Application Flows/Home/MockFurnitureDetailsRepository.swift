//
//  MockFurnitureDetailsRepository.swift
//  R&D KMM Retail
//

import Foundation
@testable import Retail

class MockFurnitureDetailsRepository: FurnitureDetailsRepository {

    var fetchResult: FurnitureViewModel?

    func getDetails(completion: @escaping (FurnitureViewModel) -> Void) {
        if let result = fetchResult {
            completion(result)
        } else {
            let viewModel = FurnitureVariationViewModel(
                id: "0",
                imageUrls: [""],
                title: "Some test furniture",
                category: "Sample category",
                currency: "$",
                price: 1234.0,
                isNew: true
            )
            let fakeViewModel = FurnitureViewModel(
                variations: [viewModel],
                description: "Sample description",
                averageReview: 4.5,
                reviewCount: "255"
            )
            completion(fakeViewModel)
        }
    }
}
