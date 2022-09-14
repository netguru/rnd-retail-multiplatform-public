//
//  OnboardingViewModelTests.swift
//  R&D KMM Retail
//

import XCTest

@testable import Retail

class OnboardingViewModelTests: XCTestCase {
    var mockOnboardingService: MockOnboardingService!
    var sut: OnboardingViewModel!

    let fixturePages: [OnboardingPage] = [
        OnboardingPage(title: .common.onboarding_title_1.localized,
                       description: .common.onboarding_message_1.localized,
                       image: .commonImages.onboarding_clock.image
        ),
        OnboardingPage(title: .common.onboarding_title_2.localized,
                       description: .common.onboarding_message_2.localized,
                       image: .commonImages.onboarding_chair.image
        ),
        OnboardingPage(title: .common.onboarding_title_3.localized,
                       description: .common.onboarding_message_3.localized,
                       image: .commonImages.chair_placeholder.image
        ),
        OnboardingPage(title: "four",
                       description: "description",
                       image: .commonImages.onboarding_clock.image
        )
    ]

    override func setUpWithError() throws {
        mockOnboardingService = MockOnboardingService()
        sut = OnboardingViewModel(
            pages: fixturePages,
            onboardingService: mockOnboardingService
        )
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        try super.tearDownWithError()
        sut = nil
    }

    func testFirstPageIsShownInitially() {
        XCTAssertEqual(sut.currentIndex, 0, "The onboarding should begin from the first page")
    }

    func testHandleNextPage_NonLastPage() {
        // given:
        sut.currentIndex = 0

        // when:
        sut.handleNextPage()

        // then:
        XCTAssertEqual(sut.currentIndex, 1)
        XCTAssertFalse(sut.isOnLastPage)
    }

    func testHandleNextPage_LastPage() {
        // given:
        let lastIndex = fixturePages.count - 1
        sut.currentIndex = lastIndex

        // when:
        sut.handleNextPage()

        // then:
        XCTAssertEqual(sut.currentIndex, lastIndex)
        XCTAssertTrue(sut.isOnLastPage)
    }

    func testHandleSkip() {
        // given:
        sut.currentIndex = 0

        // when:
        sut.handleSkip()

        // then:
        mockOnboardingService.verifyCall(withIdentifier: "finishOnboarding")
    }

    func testNextButtonTitle_NonLastPage() {
        // given:
        sut.currentIndex = fixturePages.startIndex

        // when:
        sut.handleNextPage()

        // then:
        XCTAssertEqual(sut.nextButtonTitle, .common.common_continue.localized, "Wrong button title")
    }

    func testNextButtonTitle_ToLastPage() {
        // given:
        sut.currentIndex = fixturePages.endIndex - 1

        // when:
        sut.handleNextPage()

        // then:
        XCTAssertEqual(sut.nextButtonTitle, .common.common_finish.localized, "Wrong button title")
    }

    func testNextButtonTitle_backFromLastPage() {
        // given:
        sut.currentIndex = fixturePages.count - 1

        // when:
        sut.currentIndex -= 1

        // then:
        XCTAssertEqual(sut.nextButtonTitle, .common.common_continue.localized, "Wrong button title")
    }

    func testFinishingOnboarding() {
        //  when:
        sut.finishOnboarding()

        //  then:
        mockOnboardingService.verifyCall(withIdentifier: "finishOnboarding")
    }
}
