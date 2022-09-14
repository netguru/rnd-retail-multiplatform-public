//
//  MockOnboardingViewModel.swift
//  R&D KMM Retail
//

import Foundation

@testable import Retail

final class MockOnboardingViewModel: OnboardingViewModel {
    func setState(isOnLastPage: Bool, currentIndex: Int, nextButtonTitle: String) {
        self.isOnLastPage = isOnLastPage
        self.currentIndex = currentIndex
        self.nextButtonTitle = nextButtonTitle
    }
}
