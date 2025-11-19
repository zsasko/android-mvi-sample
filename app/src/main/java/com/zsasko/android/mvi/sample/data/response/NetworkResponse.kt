package com.zsasko.android.mvi.sample.data.response

sealed class NetworkResponse<out T> {
    class Success<out T>(val data: T) : NetworkResponse<T>()
    class Error(val errorMessage: String) : NetworkResponse<Nothing>()
}