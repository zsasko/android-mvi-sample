package com.zsasko.android.mvi.sample.data.repository

import android.content.Context
import com.zsasko.android.mvi.sample.R
import com.zsasko.android.mvi.sample.api.ApiService
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import com.zsasko.android.mvi.sample.data.response.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CommentsRepository {
    suspend fun getComments(): NetworkResponse<List<CommentsResponse>?>
}

class CommentsRepositoryImpl(
    val context: Context,
    val dispatcher: CoroutineDispatcher,
    val apiService: ApiService
) :
    CommentsRepository {

    override suspend fun getComments(): NetworkResponse<List<CommentsResponse>?> {
        return withContext(dispatcher) {
            try {
                val response = apiService.getComments()
                if (response.isSuccessful) {
                    NetworkResponse.Success(response.body() ?: emptyList())
                } else {
                    NetworkResponse.Error(response.toString())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                NetworkResponse.Error(context.getString(R.string.general_network_error_occurred))
            }
        }
    }

}