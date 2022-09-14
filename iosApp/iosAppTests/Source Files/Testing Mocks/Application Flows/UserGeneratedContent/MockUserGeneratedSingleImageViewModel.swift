//
//  MockUserGeneratedSingleImageViewModel.swift
//  R&D KMM Retail
//

import common
import Foundation

@testable import Retail

final class MockUserGeneratedSingleImageViewModel: UserGeneratedSingleImageViewModel {

    func mockCompleteProduct() {
        productName = "Jim Raynor"
        imageUrl = MockImages.mockUserContentUrls[0]
    }

    func mockLongProductName() {
        productName = "Jim Raynor is a former terran marshal turned rebel, who has become one of the major figures in the Koprulu sector"
        imageUrl = MockImages.mockUserContentUrls[0]
    }
}
