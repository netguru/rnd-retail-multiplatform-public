//
//  MockUserGeneratedContentViewModel.swift
//  R&D KMM Retail
//

import common
import Foundation

@testable import Retail

final class MockUserGeneratedContentViewModel: UserGeneratedContentViewModel {

    func mockCompleteProduct() {
        productName = "Bartolomeo"
        imageUrls = MockImages.mockUserContentUrls
    }

    func mockNoImage() {
        productName = "Bartolomeo"
        imageUrls = [String]()
    }

    func mockLongProductName() {
        productName = "Bartolomeo is a captain of the pirate crew called Barto Club"
        imageUrls = MockImages.mockUserContentUrls
    }
}
