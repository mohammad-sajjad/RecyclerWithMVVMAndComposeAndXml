package com.blueapp_compose.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.blueapp_compose.models.ProductItems
import com.blueapp_compose.theme.Dimens


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductItem class.
 */

@Composable
fun ProductItem(item: ProductItems) {
    Log.e("TAG", "ProductItem: $item", )
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD2E7E1)),
        shape = RoundedCornerShape(Dimens.dp_16),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.dp_8)
        ) {
            Image(
                painter = rememberAsyncImagePainter(item.image),
                contentDescription = item.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(Dimens.dp_60)
                    .clip(RoundedCornerShape(Dimens.dp_8))
            )
            Spacer(modifier = Modifier.width(Dimens.dp_8))

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(Dimens.dp_4))
                Text(
                    text = item.description,
                    fontWeight = FontWeight.Light,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodySmall
                )
            }

        }
    }
}