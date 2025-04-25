package com.blueapp_compose

import com.blueapp_compose.core.DataFetchState
import com.blueapp_compose.remote.ApiResponseCallback
import retrofit2.Call


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ApiService class.
 */
interface ApiDataContract {
    fun getImages(page: Int, perPage: Int, query: String, responseCallback: ApiResponseCallback<ImagesResponseModel>)
}