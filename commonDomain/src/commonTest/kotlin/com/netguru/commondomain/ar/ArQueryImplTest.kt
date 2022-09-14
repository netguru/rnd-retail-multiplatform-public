package com.netguru.commondomain.ar

import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class ArQueryImplTest {

    private val arStore: Store<ArState> = Store(ArState())

    private val arModel = "bartolomeo_grey_beige"
    private val loading = Loading.Idle

    @BeforeTest
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN ar model WHEN getting ar state THEN empty model path is emitted `() = runTest {
        val arQuery = createQuery()
        var model: String? = null

        val job = arQuery.collectArModel { model = it }
        advanceUntilIdle()
        job.cancel()

        assertTrue { model == "models/.glb" || model == ".usdz" }
    }

    @Test
    fun `GIVEN ar model and loading WHEN get ar info THEN emit not empty state`() = runTest {
        arStore.update {
            ArState(
                model = arModel,
                loading = loading
            )
        }

        val arQuery = createQuery()

        var collectedModel: String? = null
        var collectedLoading: Loading? = null

        val modelJob = arQuery.collectArModel { collectedModel = it }
        val loadingJob = arQuery.collectArLoadingState { collectedLoading = it }

        advanceUntilIdle()
        modelJob.cancel()
        loadingJob.cancel()

        assertTrue { collectedModel == "models/$arModel.glb" || collectedModel == "$arModel.usdz" }
        assertEquals(loading, collectedLoading)
    }

    private fun TestScope.createQuery(): ArQuery =
        ArQueryImpl(arStore, this)
}
