//
//  UserGeneratedSingleImageViewControllerTest.swift
//  R&D KMM Retail
//

import SDWebImageMockPlugin
import SnapshotTesting
import XCTest

@testable import Retail

final class UserGeneratedSingleImageViewControllerTest: XCTestCase {
    var mockViewModel: MockUserGeneratedSingleImageViewModel!
    var sut: ViewController<UserGeneratedSingleImage>!
    var imageMocker = ImageCacheMocker()

    override func setUp() {
        super.setUp()
        let mockViewModel = MockUserGeneratedSingleImageViewModel(
            userSingleImageQuery: UserSingleImageQueryMock(),
            userSingleImageService: UserSingleImageServiceMock()
        )
        sut = ViewController(view: UserGeneratedSingleImage(viewModel: mockViewModel))
        self.mockViewModel = mockViewModel
        imageMocker.prepareTesting(for: UserGeneratedSingleImageViewControllerTest.self)
    }

    override func tearDown() {
        sut = nil
    }

    func testSnapshots() {
        // given
        mockViewModel.mockCompleteProduct()

        // then
        sut.performSnapshotTests(named: "UserGeneratedSingleImageView-complete-product")
    }

    func testSnapshots_LongProductName() {
        // given
        mockViewModel.mockLongProductName()

        // then
        sut.performSnapshotTests(named: "UserGeneratedSingleImageView-long-product-name")
    }
}
