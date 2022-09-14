//
//  DetailsViewModelTests.swift
//  R&D KMM Retail
//

import XCTest

@testable import Retail

class DetailsViewModelTests: XCTestCase {
    var mockDetailsService: MockDetailsService!
    var sut: MockDetailsViewModel!

    override func setUpWithError() throws {
        mockDetailsService = MockDetailsService()
        sut = MockDetailsViewModel(
            productDetailsService: mockDetailsService
        )
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        try super.tearDownWithError()
        sut = nil
    }

    func testHandleBack() {
        // given:
        sut.handleBack()

        // then:
        mockDetailsService.verifyCall(withIdentifier: "handleBack")
    }

    func testHandleFabricCustomize() {
        // given
        sut.handleFabricCustomize(variantName: "test")

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleFabricCustomize")
    }

    func testHandleWoodCustomize() {
        // given
        sut.handleWoodCustomize(variantName: "test")

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleWoodCustomize")
    }

    func testHandleSizeCustomize() {
        // given
        sut.handleSizeCustomize(variantName: "test")

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleSizeCustomize")
    }

    func testHandleReset() {
        // given
        sut.handleReset()

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleReset")
    }

    func testHandlePlus() {
        // given
        sut.handlePlus()

        // then
        mockDetailsService.verifyCall(withIdentifier: "handlePlus")
    }

    func testHandleMinus() {
        // given
        sut.handleMinus()

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleMinus")
    }

    func testHandleARViewClick() {
        // given
        sut.handleARViewClick()

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleARViewClick")
    }

    func testHandleShowAllClick() {
        // given
        sut.handleShowAllClick()

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleShowAllClick")
    }

    func testHandleShowFullscreenImage() {
        // given
        sut.handleShowFullscreenImage(imageUrl: "test")

        // then
        mockDetailsService.verifyCall(withIdentifier: "handleShowFullscreenImage")
    }

    func testFormattedTotalPrice() {
        // given
        sut.mockCompleteProduct()

        // then
        XCTAssertEqual(sut.formattedTotalPrice(), "€44.44")
    }
}
