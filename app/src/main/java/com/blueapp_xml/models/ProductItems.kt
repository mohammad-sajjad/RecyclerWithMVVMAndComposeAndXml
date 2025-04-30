package com.blueapp_xml.models

import com.blueapp_xml.utils.CardConstants
import com.blueapp_xml.core.RecyclerViewListItem


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductItems class.
 */
data class ProductItems(
    val title: String,
    val image: String,
    val description: String,
    val category: String
): RecyclerViewListItem {
    override fun getViewType() = CardConstants.PRODUCT_ITEMS

    override fun getUnique() = this
}
