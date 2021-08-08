package com.gketdev.gazorpazorp.repository

import com.gketdev.gazorpazorp.network.DataState
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <T : Any> apiCall(call: suspend () -> T): DataState<T> {
        return try {
            val response = call()
            DataState.Success(response)
        } catch (exception: Throwable) {
            DataState.Error(exception)
        }
    }
    protected suspend fun <T : Any> apiCallResponse(call: suspend () -> Response<T>): DataState<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    DataState.Success(body)
                else
                    DataState.Error(code = response.code(), message = response.message())
            } else {
                DataState.Error(code = response.code(), message = response.message())
            }
        } catch (exception: Throwable) {
            DataState.Error(error = exception, message = exception.message.toString())
        }
    }
}