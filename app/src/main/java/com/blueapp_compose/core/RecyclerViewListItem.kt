package com.blueapp_compose.core


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: RecyclerViewListItems class.
 */
interface RecyclerViewListItem {
    fun getViewType(): Int
    fun getUnique(): Any
}