package com.gketdev.gazorpazorp.network

sealed class DataState<out T> {
    class Success<out T>(val response: T) : DataState<T>()
    class Error(
        val error: Throwable? = null,
        val code: Int? = null,
        val message: String? = null
    ) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}