package com.zsasko.android.mvi.sample.data.intents

sealed class CommentIntent {
    class ReloadData() : CommentIntent()
}