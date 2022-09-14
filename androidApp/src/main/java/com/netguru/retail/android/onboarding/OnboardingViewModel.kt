package com.netguru.retail.android.onboarding

import androidx.lifecycle.ViewModel
import com.netguru.commondomain.onboarding.OnboardingService
import com.netguru.retail.Provider

class OnboardingViewModel(
    private val onboardingService: OnboardingService = Provider.onboardingService
) : ViewModel() {
    fun handleFinishOnboarding() {
        onboardingService.finishOnboarding()
    }
}
