//
//  UserGeneratedContentViewControllerTest.swift
//  R&D KMM Retail
//

import SDWebImageMockPlugin
import SnapshotTesting
import XCTest

@testable import Retail

final class UserGeneratedContentViewControllerTest: XCTestCase {
    var mockViewModel: MockUserGeneratedContentViewModel!
    var sut: ViewController<UserGeneratedContentView>!
    var imageMocker = ImageCacheMocker()

    override func setUp() {
        super.setUp()
        let mockViewModel = MockUserGeneratedContentViewModel(
            userContentQuery: UserContentQueryMock(),
            userContentService: UserContentServiceMock()
        )
        sut = ViewController(view: UserGeneratedContentView(viewModel: mockViewModel))
        self.mockViewModel = mockViewModel
        imageMocker.prepareTesting(for: UserGeneratedContentViewControllerTest.self)
    }

    override func tearDown() {
        sut = nil
    }

    func testSnapshots() {
        // given
        mockViewModel.mockCompleteProduct()

        // then
        sut.performSnapshotTests(named: "UserGeneratedContentView-complete-product")
    }

    func testSnapshots_NoImage() {
        // given
        mockViewModel.mockNoImage()

        // then
        sut.performSnapshotTests(named: "UserGeneratedContentView-no-image")
    }

    func testSnapshots_LongProductName() {
        // given
        mockViewModel.mockLongProductName()

        // then
        sut.performSnapshotTests(named: "UserGeneratedContentView-long-product-name")
    }
}
