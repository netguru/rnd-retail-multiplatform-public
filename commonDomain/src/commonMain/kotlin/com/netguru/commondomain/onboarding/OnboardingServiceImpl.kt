package com.netguru.commondomain.onboarding

import com.netguru.commondomain.data.PreferencesDataSource
import com.netguru.commondomain.preferences.Preferences.ONBOARDING_KEY
import com.netguru.commondomain.router.MainRouter
import kotlinx.coroutines.*

internal class OnboardingServiceImpl(
    private val mainRouter: MainRouter,
    private val preferencesDataSource: PreferencesDataSource,
    private val scope: CoroutineScope
) : OnboardingService, CoroutineScope by scope {

    override fun finishOnboarding(): Job = launch {
        preferencesDataSource.saveBoolValue(ONBOARDING_KEY, false)
        withContext(Dispatchers.Main) {
            mainRouter.toBottomNav()
        }
    }
}
