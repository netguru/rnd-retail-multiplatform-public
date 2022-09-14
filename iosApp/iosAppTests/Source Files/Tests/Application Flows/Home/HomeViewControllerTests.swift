//
//  HomeViewControllerTests.swift
//  R&D KMM Retail
//

import SDWebImageMockPlugin
import SnapshotTesting
import XCTest

@testable import Retail

final class HomeViewControllerTests: XCTestCase {
    var mockViewModel: MockShopViewModel!
    var sut: ViewController<HomeView>!
    var imageMocker = ImageCacheMocker()

    override func setUp() {
        super.setUp()
        let mockViewModel = MockShopViewModel(shopQuery: ShopQueryMock(), shopService: ShopServiceMock())
        sut = ViewController(view: HomeView(viewModel: mockViewModel))
        self.mockViewModel = mockViewModel
        imageMocker.prepareTesting(for: HomeViewControllerTests.self)
    }

    override func tearDown() {
        sut = nil
    }

    func testSnapshots() {
        // given
        mockViewModel.mockBothCollections()

        // then
        sut.performSnapshotTests(named: "HomeView-filled-collections")
    }

    func testSnapshots_EmptyCollections() {
        // given
        mockViewModel.mockBothEmptyCollections()

        // then
        sut.performSnapshotTests(named: "HomeView-empty-collections")
    }

    func testSnapshots_SingleRecommendedFurniture() {
        // given
        mockViewModel.mockOneRecommendedFurniture()

        // then
        sut.performSnapshotTests(named: "HomeView-leading-aligned-single-recommended-furniture")
    }

    func testSnapshots_SingleRecentlyViewedFurniture() {
        // given
        mockViewModel.mockOneRecentlyViewedFurniture()

        // then
        sut.performSnapshotTests(named: "HomeView-single-recently-viewed-furniture")
    }
}
