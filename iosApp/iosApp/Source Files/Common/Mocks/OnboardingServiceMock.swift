//
//  OnboardingServiceMock.swift
//  R&D KMM Retail
//

import Foundation
import common

class OnboardingServiceMock: OnboardingService {

    func finishOnboarding() -> Kotlinx_coroutines_coreJob {
        MockProvider.shared.job
    }
}
