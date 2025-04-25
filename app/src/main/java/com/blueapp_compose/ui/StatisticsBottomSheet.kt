package com.blueapp_compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.blueapp_compose.models.ProductItems
import com.blueapp_compose.theme.Dimens


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 01/05/25
 * Description: StatisticsBottomSheet class.
 */
@Composable
fun StatisticsBottomSheet(data: List<ProductItems>, currentPage: Int) {


    val charFrequency = data.joinToString("")
        .lowercase()
        .filter { it.isLetter() }
        .groupingBy { it }
        .eachCount()
        .toList()
        .sortedByDescending { it.second }
        .take(3)
        .joinToString("\n") { "${it.first} = ${it.second}" }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(Dimens.dp_24)
    ) {
        Text(
            text = "Page $currentPage (${data.size} items)",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(Dimens.dp_12))

        Text(
            text = charFrequency,
            style = MaterialTheme.typography.bodyLarge
        )
    }


}
