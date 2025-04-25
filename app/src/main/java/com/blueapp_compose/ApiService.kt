package com.blueapp_compose

import com.blueapp_compose.core.BaseApiService
import com.blueapp_compose.remote.ApiController
import com.blueapp_compose.remote.ApiResponseCallback
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ApiService class.
 */
class ApiService @Inject constructor(private val apiController: ApiController) : BaseApiService(),  ApiDataContract {
    override fun getImages(
        page: Int,
        perPage: Int,
        query: String,
        responseCallback: ApiResponseCallback<ImagesResponseModel>
    ) {
        val service = apiController.getImages(page, perPage, query)
        makeRequest(service, responseCallback)
    }
}