package com.blueapp_compose

import android.content.Context
import android.transition.Slide
import com.blueapp_compose.models.SliderItems
import com.blueapp_compose.remote.ApiResponseCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ImageRepository class.
 */

class ImageRepository @Inject constructor(private val apiService: ApiDataContract) {
    fun getImages(page: Int, perPage: Int, query: String, responseCallback: ApiResponseCallback<ImagesResponseModel>) {
        apiService.getImages(page, perPage, query, responseCallback)
    }
}