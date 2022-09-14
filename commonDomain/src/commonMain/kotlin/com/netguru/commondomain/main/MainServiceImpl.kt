package com.netguru.commondomain.main

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.PreferencesDataSource
import com.netguru.commondomain.preferences.Preferences.ONBOARDING_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class MainServiceImpl(
    private val mainStore: Store<MainState>,
    private val preferencesDataSource: PreferencesDataSource,
    private val scope: CoroutineScope
) : MainService, CoroutineScope by scope {

    override fun loadShowOnboarding(): Job = launch {
        mainStore.update {
            it.copy(
                loading = Loading.InProgress
            )
        }
        runCatching {
            preferencesDataSource.getBoolValue(ONBOARDING_KEY, true)
        }.onSuccess { shouldShowOnboarding ->
            mainStore.update {
                it.copy(
                    shouldShowOnboarding = shouldShowOnboarding,
                    loading = Loading.Idle
                )
            }
        }.onFailure { error ->
            mainStore.update {
                it.copy(
                    shouldShowOnboarding = true,
                    loading = Loading.Failed(error)
                )
            }
        }
    }
}
