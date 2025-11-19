package com.zsasko.android.mvi.sample.data.repository

import android.content.Context
import com.zsasko.android.mvi.sample.api.ApiService
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import com.zsasko.android.mvi.sample.data.response.NetworkResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class CommentsRepositoryTest {

    @MockK
    private lateinit var apiService: ApiService
    private lateinit var repository: CommentsRepository
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var context: Context

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        context = mockk(relaxed = true)
        repository = CommentsRepositoryImpl(context, testDispatcher, apiService)
    }

    @Test
    fun `getComments returns success when API is successful`() = testScope.runTest {
        val mockComments = listOf(
            CommentsResponse(
                postId = 1,
                id = 1,
                name = "Id labore ex et quam laborum",
                email = "Eliseo@gardner.biz",
                body = "Laudantium enim quasi est quidem magnam voluptate ipsam eos."
            )
        )
        coEvery { apiService.getComments() } returns Response.success(mockComments)

        val result = repository.getComments()

        assert(result is NetworkResponse.Success)
        Assert.assertEquals(mockComments, (result as NetworkResponse.Success).data)
    }

    @Test
    fun `getComments returns error when API fails`() = testScope.runTest {
        coEvery { apiService.getComments() } returns Response.error(404, mockk(relaxed = true))

        val result = repository.getComments()

        assert(result is NetworkResponse.Error)
    }

    @Test
    fun `getComments returns error when exception is thrown`() = testScope.runTest {
        val errorMessage = "Network failure"
        coEvery { context.getString(any<Int>()) } returns errorMessage
        coEvery { apiService.getComments() } throws RuntimeException(errorMessage)

        val result = repository.getComments()

        assert(result is NetworkResponse.Error)
        Assert.assertEquals(errorMessage, (result as NetworkResponse.Error).errorMessage)
    }
}