package com.blueapp_compose.repository

import com.blueapp_compose.R
import com.blueapp_compose.models.CategoryItems
import com.blueapp_compose.models.ProductItems
import com.blueapp_compose.utils.ProductDataSource
import com.blueapp_xml.utils.StringResourceProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductsRepository class.
 */
@Singleton
class ProductsRepository @Inject constructor(private val productDataSource: ProductDataSource, private val stringResourceProvider: StringResourceProvider) {

    fun getCategories(): List<CategoryItems> {
        return listOf(
            CategoryItems(stringResourceProvider.getString(R.string.category_tv), R.drawable.tv),
            CategoryItems(stringResourceProvider.getString(R.string.category_audio), R.drawable.audio),
            CategoryItems(stringResourceProvider.getString(R.string.category_laptop), R.drawable.laptop),
            CategoryItems(stringResourceProvider.getString(R.string.category_mobile), R.drawable.mobile),
            CategoryItems(stringResourceProvider.getString(R.string.category_gaming), R.drawable.gaming),
            CategoryItems(stringResourceProvider.getString(R.string.category_appliances), R.drawable.appliances),
        )
    }

    fun prepareProductsData(category: String? = null): List<ProductItems> {
        val jsonString = productDataSource.getJsonFromAsset()
        val productListType = object : TypeToken<List<ProductItems>>() {}.type
        val allProducts: List<ProductItems> = Gson().fromJson(jsonString, productListType)
        if (category == null) {
            return allProducts
        } else {
            return  allProducts.filter { it.category == category }
        }
    }
}