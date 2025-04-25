package com.blueapp_compose.remote

import com.blueapp_compose.ImagesResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: ApiController class.
 */
interface ApiController {
    @GET("https://pixabay.com/api/?key=49804947-5c9bda4364e0e0e7c7e852a57&&image_type=photo&pretty=true")
    fun getImages(@Query("page") page: Int, @Query("per_page") perPage: Int, @Query("q") query: String): Call<ImagesResponseModel>
}