package com.gketdev.gazorpazorp.repository

import com.gketdev.gazorpazorp.network.NetworkState
import retrofit2.Response

abstract class BaseRepository {
    protected suspend fun <T : Any> apiCall(call: suspend () -> T): NetworkState<T> {
        return try {
            val response = call()
            NetworkState.Success(response)
        } catch (exception: Throwable) {
            NetworkState.Error(exception)
        }
    }
    protected suspend fun <T : Any> apiCallResponse(call: suspend () -> Response<T>): NetworkState<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    NetworkState.Success(body)
                else
                    NetworkState.Error(code = response.code(), message = response.message())
            } else {
                NetworkState.Error(code = response.code(), message = response.message())
            }
        } catch (exception: Throwable) {
            NetworkState.Error(error = exception, message = exception.message.toString())
        }
    }
}