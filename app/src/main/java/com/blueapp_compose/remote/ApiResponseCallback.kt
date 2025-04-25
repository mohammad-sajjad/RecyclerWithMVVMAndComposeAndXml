package com.blueapp_compose.remote

import com.blueapp_compose.error.StandardError
import retrofit2.Response


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: ApiResponseCallback class.
 */
interface ApiResponseCallback<T> {
    fun onSuccess(response: T)
    fun onError(error: StandardError)

}