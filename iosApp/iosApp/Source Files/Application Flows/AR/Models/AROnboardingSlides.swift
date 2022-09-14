//
//  AROnboardingSlides.swift
//  R&D KMM Retail
//

import UIKit

/// A structure containing a collection of onboarding slides.
struct AROnboardingSlides {

    /// A collection of onboarding slides.
    let collection = [
        AROnboardingSlide(
            image: .commonAssets.ar_onboarding_step_1.image,
            message: .common.ar_onboarding_step_1.localized
        ),
        AROnboardingSlide(
            image: .commonAssets.ar_onboarding_step_2.image,
            message: .common.ar_onboarding_step_2.localized
        ),
        AROnboardingSlide(
            image: .commonAssets.ar_onboarding_step_4.image,
            message: .common.ar_onboarding_step_4.localized
        ),
        AROnboardingSlide(
            image: .commonAssets.ar_onboarding_step_5.image,
            message: .common.ar_onboarding_step_5.localized
        ),
        AROnboardingSlide(
            image: .commonAssets.ar_onboarding_step_6.image,
            message: .common.ar_onboarding_step_6.localized
        )
    ]
}
