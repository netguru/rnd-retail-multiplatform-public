//
//  FakeDetailsViewModel.swift
//  R&D KMM Retail
//

import Foundation
import Mimus

@testable import Retail

final class FakeDetailsViewModel: DetailsViewModel, Mock {

    var storage = Storage()

    override func fetchData() {
        selectedFurnitureVariation = FurnitureVariationViewModel(
            id: "1",
            imageUrls: ["1"],
            title: "Some furniture",
            category: "chairs",
            currency: "$",
            price: 100.0,
            isNew: true
        )
        recordCall(withIdentifier: "details-view-sheet-open")
    }

    func titleWithOneLines() {
        selectedFurnitureVariation = FurnitureVariationViewModel(
            id: "1",
            imageUrls: ["1"],
            title: "Some furniture",
            category: "chairs",
            currency: "$",
            price: 100.0,
            isNew: true)
    }

    func titleWithTwoLines() {
        selectedFurnitureVariation = FurnitureVariationViewModel(
            id: "1",
            imageUrls: ["1"],
            title: "Some furniture for testing Some furniture for testing Some furniture for testing",
            category: "chairs",
            currency: "$",
            price: 100.0,
            isNew: true)
    }
}
