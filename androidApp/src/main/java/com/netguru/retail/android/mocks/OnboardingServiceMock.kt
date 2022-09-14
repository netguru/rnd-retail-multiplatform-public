package com.netguru.retail.android.mocks

import com.netguru.commondomain.onboarding.OnboardingService
import kotlinx.coroutines.Job

class OnboardingServiceMock : OnboardingService {
    override fun finishOnboarding(): Job = Job()
}
