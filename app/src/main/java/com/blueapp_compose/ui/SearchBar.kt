package com.blueapp_compose.ui

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.blueapp_compose.constants.Strings
import com.blueapp_compose.theme.Dimens
import com.blueapp_compose.theme.FocusedContainerColor
import com.blueapp_compose.theme.FocusedIndicatorColor
import com.blueapp_compose.theme.UnfocusedContainerColor
import com.blueapp_compose.theme.UnfocusedIndicatorColor
import com.blueapp_compose.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = Strings.search_hint)
        },
        placeholder = {
            Text(Strings.search_hint)
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(White, shape = RoundedCornerShape(Dimens.dp_8)),
        shape = RoundedCornerShape(Dimens.dp_8),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = UnfocusedContainerColor,
            focusedContainerColor = FocusedContainerColor,
            focusedIndicatorColor = FocusedIndicatorColor,
            unfocusedIndicatorColor = UnfocusedIndicatorColor
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchBarPreview() {
    var searchText by remember { mutableStateOf("") }

    SearchBar(
        searchQuery = searchText,
        onSearchChange = { searchText = it }
    )
}
