package com.blueapp_compose.utils

import android.content.Context
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductDataSource class.
 */
class ProductDataSource @Inject constructor(private val context: Context) {
    fun getJsonFromAsset(): String {
        return context.assets.open("products.json").bufferedReader().use { it.readText() }
    }
}