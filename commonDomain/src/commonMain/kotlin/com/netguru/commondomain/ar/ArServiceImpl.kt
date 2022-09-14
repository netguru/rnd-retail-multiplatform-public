package com.netguru.commondomain.ar

import com.netguru.commondomain.ar.model.ArModelConfiguration
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.ArDataSource
import com.netguru.commondomain.data.PreferencesDataSource
import com.netguru.commondomain.preferences.Preferences.AR_ONBOARDING_KEY
import com.netguru.commondomain.router.MainRouter
import kotlinx.coroutines.*

internal class ArServiceImpl(
    private val arStore: Store<ArState>,
    private val arDataSource: ArDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val mainRouter: MainRouter,
    private val scope: CoroutineScope
) : ArService, CoroutineScope by scope {

    override fun loadAr(arModelConfiguration: ArModelConfiguration): Job = launch {
        arStore.update {
            it.copy(loading = Loading.InProgress)
        }
        try {
            val arModel = async { arDataSource.getArModel(arModelConfiguration) }
            val shouldShowOnboarding =
                async { preferencesDataSource.getBoolValue(AR_ONBOARDING_KEY, true) }

            val newState = ArState(
                model = arModel.await(),
                shouldShowOnboarding = shouldShowOnboarding.await(),
                loading = Loading.Idle
            )
            arStore.update { newState }
        } catch (e: Exception) {
            arStore.update {
                it.copy(loading = Loading.Failed(e))
            }
            if (e is CancellationException) {
                throw e
            }
        }
    }

    override fun finishArOnboarding(): Job = launch {
        val shouldShowArOnboarding = false
        preferencesDataSource.saveBoolValue(AR_ONBOARDING_KEY, shouldShowArOnboarding)
        arStore.update {
            it.copy(
                shouldShowOnboarding = shouldShowArOnboarding
            )
        }
    }

    override fun goBack(): Job = launch {
        arStore.reset()
        withContext(Dispatchers.Main) {
            mainRouter.back()
        }
    }
}
