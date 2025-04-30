package com.blueapp_xml.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueapp_xml.models.CategoryItems
import com.blueapp_xml.models.ProductItems
import com.blueapp_xml.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductsViewmodel class.
 */
@HiltViewModel
class ProductsViewmodel @Inject constructor(private val repository: ProductsRepository): ViewModel() {
    private val _categories = MutableLiveData<List<CategoryItems>>()
    val categories: LiveData<List<CategoryItems>> = _categories

    private val _products = MutableLiveData<List<ProductItems>>()
    val products: LiveData<List<ProductItems>> = _products

    private var allProducts = emptyList<ProductItems>()


    private val _productCount = MutableLiveData<Int>()

    init {
        loadCategories()
        fetchProducts()
    }

    fun loadCategories() {
        viewModelScope.launch {
            _categories.value = repository.getCategories()
        }
    }

    fun fetchProducts(categoryName: String? = null) {
        viewModelScope.launch {
            _products.value = emptyList<ProductItems>()
             allProducts = repository.prepareProductsData(categoryName)
            _products.value = allProducts
        }
    }

    fun onSliderItemChanged(position: Int) {
        val selectedItem = _categories.value?.getOrNull(position)
        fetchProducts(selectedItem?.categoryName)
        Log.e("TAG", "onSliderItemChanged: $selectedItem", )
    }

    fun filterProducts(query: String) {
        val filtered = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.title.contains(query, ignoreCase = true)
            }
        }
        _products.value = filtered
    }

}