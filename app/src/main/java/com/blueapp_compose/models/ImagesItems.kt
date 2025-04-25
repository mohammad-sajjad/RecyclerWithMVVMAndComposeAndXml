package com.blueapp_compose.models

import com.blueapp_compose.CardConstants
import com.blueapp_compose.core.RecyclerViewListItem


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ImagesItems class.
 */
data class ImagesItems(
    val image: String,
    val title: String,
    val details: String,
): RecyclerViewListItem {
    override fun getViewType() = CardConstants.IMAGE_LIST_ITEM
    override fun getUnique() = this
}