package com.zsasko.android.mvi.sample

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.zsasko.android.mvi.sample.data.repository.CommentsRepository
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import com.zsasko.android.mvi.sample.data.response.NetworkResponse
import com.zsasko.android.mvi.sample.di.NetworkModule
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(NetworkModule::class)
class CommentsScreenWithNetworkSuccess {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @BindValue
    @JvmField
    val repository: CommentsRepository = mockk {
        coEvery { getComments() } returns NetworkResponse.Success(
            listOf(
                CommentsResponse(
                    postId = 1,
                    id = 1,
                    name = "Alice Johnson",
                    email = "alice.johnson@example.com",
                    body = "This post is really insightful and helped me understand the topic better."
                ),
                CommentsResponse(
                    postId = 2,
                    id = 2,
                    name = "Bob Smith",
                    email = "bob.smith@example.com",
                    body = "I have a question about the second paragraph. Could you clarify it?"
                )
            )
        )
    }

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun comments_list_is_displayed_when_network_returns_data_without_error() = runTest {
        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag(TEST_TAG_COMMENTS_SCREEN_COMMENTS_LIST),
            timeoutMillis = 5_000
        )
        composeTestRule.onNodeWithTag(TEST_TAG_COMMENTS_SCREEN_COMMENTS_LIST)
            .assertIsDisplayed()
        composeTestRule.onNodeWithTag(TEST_TAG_ERROR_SCREEN_BUTTON_RELOAD)
            .assertIsNotDisplayed()
    }
}
