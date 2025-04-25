package com.blueapp_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blueapp_compose.models.CategoryItems
import com.blueapp_compose.models.ProductItems
import com.blueapp_compose.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductListViewModel class.
 */

@HiltViewModel
class ProductsViewmodel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    private val _categories = MutableStateFlow<List<CategoryItems>>(emptyList())
    val categories: StateFlow<List<CategoryItems>> = _categories.asStateFlow()

    private val _allProducts = MutableStateFlow<List<ProductItems>>(emptyList())

    private val _searchQuery = MutableStateFlow("")

    val products: StateFlow<List<ProductItems>> = combine(_allProducts, _searchQuery) { all, query ->
        if (query.isBlank()) all
        else all.filter { it.title.contains(query, ignoreCase = true) }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        loadCategories()
        fetchProducts()
    }

    private fun loadCategories() {
        viewModelScope.launch {
                val categories = repository.getCategories()
                _categories.value = categories
        }
    }

    fun fetchProducts(categoryName: String? = null) {
        viewModelScope.launch {
                val products = repository.prepareProductsData(categoryName)
                _allProducts.value = products

        }
    }

    fun onSliderItemChanged(position: Int) {
        val selectedItem = _categories.value.getOrNull(position)
        fetchProducts(selectedItem?.categoryName)
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
