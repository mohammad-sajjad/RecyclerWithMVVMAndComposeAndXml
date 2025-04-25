package com.blueapp_compose.ui

import android.graphics.Paint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.blueapp_compose.theme.BgColor
import com.blueapp_compose.theme.Dimens
import com.blueapp_compose.viewmodel.ProductsViewmodel


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductsScreen class.
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductsViewmodel = hiltViewModel(),
) {
    val categories by viewModel.categories.collectAsState()
    val products by viewModel.products.collectAsState()

    var searchQuery by rememberSaveable { mutableStateOf("") }
    val listState = rememberLazyListState()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var currentPage = rememberSaveable { mutableIntStateOf(0) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()


    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = bottomSheetState
        ) {
            StatisticsBottomSheet(
                data = products.toMutableList(),
                currentPage = currentPage.intValue
            )
        }
    }
    Box(modifier = modifier
        .padding(top = Dimens.dp_24, bottom = Dimens.dp_24)
        .background(BgColor)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(Dimens.dp_4),
            contentPadding = PaddingValues(horizontal = Dimens.dp_24, vertical = Dimens.dp_16)
        ) {
            item {
                val categoryCarouselHeight =
                    (1 - scrollBehavior.state.collapsedFraction) * Dimens.dp_250.value
                CategoryCarousel(items = categories, onPageCallChange = { position ->
                    currentPage.intValue = position + 1
                    viewModel.onSliderItemChanged(position)
                }, modifier = Modifier.height(categoryCarouselHeight.dp))
            }

            stickyHeader {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchChange = {
                        searchQuery = it
                        viewModel.onSearchQueryChanged(it)
                    }
                )
                Spacer(modifier = Modifier.height(Dimens.dp_16))
            }
            items(products.size) { index ->
                ProductItem(products[index])
            }
        }

        StatisticsFloatingButton(modifier = Modifier.align(Alignment.BottomEnd)) {
            showBottomSheet = true
        }
    }

}

