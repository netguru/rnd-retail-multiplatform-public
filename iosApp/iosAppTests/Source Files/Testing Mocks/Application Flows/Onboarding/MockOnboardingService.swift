//
//  MockOnboardingService.swift
//  R&D KMM Retail
//

import common
import Foundation
import Mimus

@testable import Retail

class MockOnboardingService: OnboardingService, Mock {

    var storage = Mimus.Storage()

    func finishOnboarding() -> Kotlinx_coroutines_coreJob {
        recordCall(withIdentifier: "finishOnboarding")
        return MockProvider.shared.job
    }
}
