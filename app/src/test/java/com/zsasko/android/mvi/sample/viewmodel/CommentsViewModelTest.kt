package com.zsasko.android.mvi.sample.viewmodel

import app.cash.turbine.test
import com.zsasko.android.mvi.sample.data.intents.CommentIntent
import com.zsasko.android.mvi.sample.data.repository.CommentsRepository
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import com.zsasko.android.mvi.sample.data.response.NetworkResponse
import com.zsasko.android.mvi.sample.data.state.CommentsUiState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CommentsViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: CommentsViewModel
    private val repository: CommentsRepository = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadData emits loading and success states`() = runTest {
        val commentsList = listOf(
            CommentsResponse(
                postId = 1,
                id = 1,
                name = "Id labore ex et quam laborum",
                email = "Eliseo@gardner.biz",
                body = "Laudantium enim quasi est quidem magnam voluptate ipsam eos."
            )
        )
        coEvery { repository.getComments() } returns NetworkResponse.Success(commentsList)
        viewModel = CommentsViewModel(repository)

        viewModel.commentsUiState.test {
            // Initial state
            assertTrue(awaitItem() is CommentsUiState.Loading)

            // Trigger load data
            viewModel.handleIntent(CommentIntent.ReloadData())

            // Loading state
            assertTrue(awaitItem() is CommentsUiState.Loading)

            // Success state
            assertTrue(awaitItem() is CommentsUiState.Loaded)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadData emits loading and error states`() = runTest {
        val errorMessage = "Network Error"
        coEvery { repository.getComments() } returns NetworkResponse.Error(errorMessage)
        viewModel = CommentsViewModel(repository)

        viewModel.commentsUiState.test {
            // Initial state
            assertTrue(awaitItem() is CommentsUiState.Loading)

            // Trigger load data
            viewModel.handleIntent(CommentIntent.ReloadData())

            // Loading state
            assertTrue(awaitItem() is CommentsUiState.Loading)

            // Error state
            val errorState = awaitItem()
            assertTrue(errorState is CommentsUiState.Error)
            assertEquals((errorState as CommentsUiState.Error).errorMessage, errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
    }
}