//
//  OnboardingViewModel.swift
//  R&D KMM Retail
//

import Foundation
import common

class OnboardingViewModel: ObservableObject {

    // MARK: Public Properties

    /// Notifies that the last page in the collection is presented.
    @Published var isOnLastPage: Bool = false

    /// A variable indicating the next button title based on the current index.
    @Published var nextButtonTitle: String = "Continue"

    /// Index of the currently displayed page.
    @Published var currentIndex: Int = 0 {
        didSet {
            handleNextButtonTitle()
            checkIfLastPage()
        }
    }

    /// The collection of OnboardingPage objects to be shown.
    let pages: [OnboardingPage]

    private let onboardingService: OnboardingService

    init(
        pages: [OnboardingPage],
        onboardingService: OnboardingService = Provider.shared.onboardingService
    ) {
        self.pages = pages
        self.onboardingService = onboardingService
    }

    // MARK: Public Methods

    /// Finishes the onboarding.
    func finishOnboarding() {
        onboardingService.finishOnboarding()
    }

    /// Calls to display the next page.
    func handleNextPage() {
        currentIndex = min(currentIndex + 1, pages.count - 1)
    }

    /// Calls to skip the onboarding.
    func handleSkip() {
        finishOnboarding()
    }
}

// MARK: - Implementation Details

private extension OnboardingViewModel {

    func handleNextButtonTitle() {
        let isNotLastPage = currentIndex < pages.count - 1
        nextButtonTitle = isNotLastPage ? .common.common_continue.localized : .common.common_finish.localized
    }

    func checkIfLastPage() {
        isOnLastPage = currentIndex == pages.count - 1
    }
}
