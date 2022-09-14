package com.netguru.commondomain.userContent

import com.netguru.commondomain.base.Store
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class UserContentQueryImplTest {
    private val userContentStore = Store(UserContentState())

    private val contentTitle = "Whinston"
    private val contentImageUrl = "https://www.nasa.gov/"

    @BeforeTest
    fun before() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @AfterTest
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN initial state WHEN get user content THEN empty content is emitted `() = runTest {
        var userContentTitle: String? = null
        var userContentImages: List<String> = emptyList()

        val userContentQuery = createQuery()

        val titleJob = userContentQuery.collectUserContentTitle { userContentTitle = it }
        val imagesJob = userContentQuery.collectUserContentImages { userContentImages = it }
        advanceUntilIdle()

        titleJob.cancel()
        imagesJob.cancel()

        assertEquals(UserContentState().title, userContentTitle)
        assertEquals(UserContentState().images.map { it.imageUrl }, userContentImages)
    }

    @Test
    fun `GIVEN title and images WHEN get user content THEN emit the user content`() = runTest {
        userContentStore.update(
            UserContentState(
                title = contentTitle,
                images = listOf(UserContentImage(contentImageUrl))
            )
        )
        var collectedTitle = ""
        var collectedImages = emptyList<String>()

        val userContentQuery = createQuery()

        val titleJob = userContentQuery.collectUserContentTitle { collectedTitle = it }
        val imagesJob = userContentQuery.collectUserContentImages { collectedImages = it }
        advanceUntilIdle()

        titleJob.cancel()
        imagesJob.cancel()

        assertEquals(contentTitle, collectedTitle)
        assertEquals(contentImageUrl, collectedImages[0])
    }

    private fun TestScope.createQuery(): UserContentQuery =
        UserContentQueryImpl(userContentStore, this)
}
