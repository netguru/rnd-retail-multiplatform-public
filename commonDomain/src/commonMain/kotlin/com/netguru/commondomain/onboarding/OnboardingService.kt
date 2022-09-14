package com.netguru.commondomain.onboarding

import kotlinx.coroutines.Job

interface OnboardingService {
    fun finishOnboarding(): Job
}
