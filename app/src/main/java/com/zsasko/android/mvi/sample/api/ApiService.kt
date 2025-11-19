package com.zsasko.android.mvi.sample.api

import com.zsasko.android.mvi.sample.ENDPIONT_COMMENTS
import com.zsasko.android.mvi.sample.data.response.CommentsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(ENDPIONT_COMMENTS)
    suspend fun getComments(): Response<List<CommentsResponse>>
}