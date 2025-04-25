package com.blueapp_compose.core

import com.blueapp_compose.error.StandardError
import com.blueapp_compose.remote.ApiResponseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.net.ssl.HttpsURLConnection


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: BaseService class.
 */
open class BaseApiService {

    fun <T> makeRequest(call: Call<T>, callback: ApiResponseCallback<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let(callback::onSuccess) ?: callback.onError(
                        StandardError("Empty response body", "Unknown error")
                    )
                } else {
                    callback.onError(response.toStandardError())
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onError(t.toStandardError())
            }
        })
    }

    // Extension for handling non-200 responses
    private fun <T> Response<T>.toStandardError(): StandardError {
        return when (code()) {
            401 -> StandardError("Session expired. Please log in again.", "Token Expired")
            else -> StandardError(message(), "Unknown Error")
        }
    }

    // Extension for Throwable errors
    private fun Throwable.toStandardError(): StandardError {
        return when (this) {
            is HttpException -> {
                val message = when (code()) {
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> "Session expired. Please log in again."
                    HttpsURLConnection.HTTP_FORBIDDEN -> "Forbidden error."
                    HttpsURLConnection.HTTP_INTERNAL_ERROR -> "Internal server error."
                    HttpsURLConnection.HTTP_BAD_REQUEST -> "Bad request."
                    else -> message().orEmpty()
                }
                StandardError(title = "Network Error", displayError = message)
            }
            else -> StandardError("Unknown Error", message.orEmpty())
        }
    }
}