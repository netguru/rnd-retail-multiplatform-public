//
//  DetailsViewControllerTests.swift
//  R&D KMM Retail
//

import SDWebImageMockPlugin
import SnapshotTesting
import XCTest

@testable import Retail

final class DetailsViewControllerTest: XCTestCase {
    var mockViewModel: MockDetailsViewModel!
    var sut: ViewController<DetailsView>!
    var imageMocker = ImageCacheMocker()

    override func setUp() {
        super.setUp()
        let mockViewModel = MockDetailsViewModel(
            productDetailsQuery: ProductDetailsQueryMock(),
            productDetailsService: ProductDetailsServiceMock()
        )
        sut = ViewController(view: DetailsView(viewModel: mockViewModel))
        self.mockViewModel = mockViewModel
        imageMocker.prepareTesting(for: DetailsViewControllerTest.self)
    }

    override func tearDown() {
        sut = nil
    }

    func testSnapshots() {
        // given
        mockViewModel.mockCompleteProduct()

        // then
        sut.performSnapshotTests(named: "DetailsView-complete-product")
    }

    func testSnapshots_LongPrice() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockLongPrice()

        // then
        sut.performSnapshotTests(named: "DetailsView-long-price")
    }

    func testSnapshots_HundredItems() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockHundredItems()

        // then
        sut.performSnapshotTests(named: "DetailsView-hundred-items")
    }

    func testSnapshots_FiveStarsRating() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockFiveStarsRating()

        // then
        sut.performSnapshotTests(named: "DetailsView-five-stars-rating")
    }

    func testSnapshots_ZeroStarsRating() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockZeroStarsRating()

        // then
        sut.performSnapshotTests(named: "DetailsView-zero-stars-rating")
    }

    func testSnapshots_ZeroHalfStarsRating() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockZeroHalfStarsRating()

        // then
        sut.performSnapshotTests(named: "DetailsView-zero-half-stars-rating")
    }

    func testSnapshots_ShortDescription() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockShortDescription()

        // then
        sut.performSnapshotTests(named: "DetailsView-short-description")
    }

    func testSnapshots_BottomSheetOpen() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockBottomSheetOpen()

        // then
        sut.performSnapshotTests(named: "DetailsView-bottom-sheet-open")
    }

    func testSnapshots_BottomSheetOpenWithOptions() {
        // given
        mockViewModel.mockCompleteProduct()
        mockViewModel.mockBottomSheetOpenWithOptions()

        // then
        sut.performSnapshotTests(named: "DetailsView-bottom-sheet-open-with-options")
    }
}
