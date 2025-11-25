package com.zsasko.android.mvi.sample.data.response

data class CommentsResponse(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)