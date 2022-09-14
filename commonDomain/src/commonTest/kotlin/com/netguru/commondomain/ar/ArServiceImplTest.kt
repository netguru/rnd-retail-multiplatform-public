package com.netguru.commondomain.ar

import app.cash.turbine.test
import com.netguru.commondomain.ar.model.ArModelConfiguration
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.data.ArDataSource
import com.netguru.commondomain.data.PreferencesDataSource
import com.netguru.commondomain.router.MainRouter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class ArServiceImplTest {

    private val arStore: Store<ArState> = Store(ArState())
    private val arDataSource = mockk<ArDataSource>()
    private val preferencesDataSource = mockk<PreferencesDataSource>(relaxed = true)
    private val mainRouter = mockk<MainRouter>()

    private val modelFromSource = "bartolomeo_grey_beige"

    @BeforeTest
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN arModel from source WHEN load ar model THEN update state with loaded model and loading status`() =
        runTest {
            arStore.stream.test {
                coEvery {
                    arDataSource.getArModel(any())
                } returns modelFromSource

                val arService = createService()
                arService.loadAr(ArModelConfiguration("", ""))

                awaitItem().run {
                    assertEquals(Loading.Idle, this.loading)
                    assertEquals("", this.model)
                }
                awaitItem().run {
                    assertEquals(Loading.InProgress, this.loading)
                    assertEquals("", this.model)
                }
                awaitItem().run {
                    assertEquals(Loading.Idle, this.loading)
                    assertEquals(modelFromSource, this.model)
                }
            }
        }

    @Test
    fun `GIVEN loaded model WHEN go back THEN state is reset to initial one`() =
        runTest {
            coEvery {
                arDataSource.getArModel(any())
            } returns modelFromSource
            coEvery {
                mainRouter.back()
            } returns Unit
            arStore.update {
                ArState(
                    model = "$modelFromSource.usdz",
                    loading = Loading.Idle
                )
            }
            val arService = createService()
            arService.goBack()
            advanceUntilIdle()

            arStore.stream.test {
                awaitItem().run {
                    assertEquals(Loading.Idle, this.loading)
                    assertEquals("", this.model)
                }
            }
        }

    private fun TestScope.createService(): ArService =
        ArServiceImpl(
            arStore,
            arDataSource,
            preferencesDataSource,
            mainRouter,
            this
        )
}
