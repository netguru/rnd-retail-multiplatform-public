package com.netguru.commondomain.userContent

import app.cash.turbine.test
import com.netguru.commondomain.base.Loading
import com.netguru.commondomain.base.Store
import com.netguru.commondomain.router.MainRouter
import com.netguru.commondomain.userContent.model.UserContentRequest
import com.netguru.commondomain.userSingleImage.UserSingleImageService
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
class UserContentServiceImplTest {

    private val userContentStore = Store(UserContentState())
    private val userContentDataSource = mockk<UserContentDataSource>()
    private val userSingleImageService = mockk<UserSingleImageService>()
    private val mainRouter = mockk<MainRouter>()

    private val imagesFromSource = listOf(
        UserContentImage(
            imageUrl = "https://www.spacex.com/"
        )
    )
    private val productTitle = "Whinston"

    @BeforeTest
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN user content title and images WHEN load user content images THEN update state with loaded title and images`() =
        runTest {
            userContentStore.stream.test {
                coEvery {
                    userContentDataSource.getUserContentImages(any())
                } returns imagesFromSource

                val userContentService = createService()
                userContentService.loadUserContent(UserContentRequest(productTitle, "0"))

                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals("", title)
                    assertEquals(emptyList(), images)
                }
                awaitItem().run {
                    assertEquals(Loading.InProgress, loading)
                    assertEquals(productTitle, title)
                    assertEquals(emptyList(), images)
                }
                awaitItem().run {
                    assertEquals(Loading.Idle, loading)
                    assertEquals(productTitle, title)
                    assertEquals(imagesFromSource, images)
                }
            }
        }

    private fun TestScope.createService(): UserContentService =
        UserContentServiceImpl(
            userContentStore,
            userContentDataSource,
            userSingleImageService,
            mainRouter,
            this
        )
}
