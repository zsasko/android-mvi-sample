package com.zsasko.android.mvi.sample.data.state

import com.zsasko.android.mvi.sample.data.response.CommentsResponse

sealed class CommentsUiState() {
    class Loaded(val data: List<CommentsResponse> = emptyList<CommentsResponse>()) :
        CommentsUiState()

    class Loading() : CommentsUiState()
    class Error(val errorMessage: String? = null) : CommentsUiState()

}
