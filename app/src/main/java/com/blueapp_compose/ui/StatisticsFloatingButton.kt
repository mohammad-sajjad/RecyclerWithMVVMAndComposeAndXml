package com.blueapp_compose.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.blueapp_compose.theme.Dimens


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 01/05/25
 * Description: StatisticsFloatingButton class.
 */
@Composable
fun StatisticsFloatingButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .padding(Dimens.dp_16),
        containerColor = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(50)
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Add",
            tint = Color.White
        )
    }
}