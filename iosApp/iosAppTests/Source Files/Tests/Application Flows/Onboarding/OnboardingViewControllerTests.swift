//
//  OnboardingViewControllerTests.swift
//  R&D KMM Retail
//

import SnapshotTesting
import XCTest

@testable import Retail
@testable import common

final class OnboardingViewControllerTest: XCTestCase {
    var mockViewModel: MockOnboardingViewModel!
    var sut: ViewController<OnboardingView>!

    override func setUp() {
        let fixturePage1 = OnboardingPage(title: .common.onboarding_title_1.localized,
                                          description: .common.onboarding_message_1.localized,
                                          image: .commonImages.onboarding_clock.image
        )
        let fixturePage2 = OnboardingPage(title: .common.onboarding_title_2.localized,
                                          description: .common.onboarding_message_2.localized,
                                          image: .commonImages.onboarding_chair.image
        )
        let fixturePage3 = OnboardingPage(title: .common.onboarding_title_3.localized,
                                          description: .common.onboarding_message_3.localized,
                                          image: .commonImages.chair_placeholder.image
        )
        let mockViewModel = MockOnboardingViewModel(
            pages: [fixturePage1, fixturePage2, fixturePage3],
            onboardingService: MockOnboardingService()
        )
        sut = ViewController(view: OnboardingView(viewModel: mockViewModel))
        self.mockViewModel = mockViewModel
    }

    override func tearDown() {
        sut = nil
    }

    func testSnapshots() {
        //  given:
        mockViewModel.setState(isOnLastPage: false, currentIndex: 0, nextButtonTitle: "Continue")

        //  then:
        sut.performSnapshotTests(named: "OnboardingViewController-slide-1")

        //  given:
        mockViewModel.setState(isOnLastPage: false, currentIndex: 1, nextButtonTitle: "Continue")

        //  then:
        sut.performSnapshotTests(named: "OnboardingViewController-slide-2")

        //  given:
        mockViewModel.setState(isOnLastPage: true, currentIndex: 2, nextButtonTitle: "Get started")

        //  then:
        sut.performSnapshotTests(named: "OnboardingViewController-slide-3")
    }
}
