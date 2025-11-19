package com.zsasko.android.mvi.sample.data.response

data class CommentsResponse(
    var postId: Int,
    var id: Int,
    var name: String,
    var email: String,
    var body: String
)