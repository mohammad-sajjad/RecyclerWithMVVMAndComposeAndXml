package com.blueapp_compose.models
import com.blueapp_compose.CardConstants
import com.blueapp_compose.core.RecyclerViewListItem
import com.google.gson.annotations.SerializedName


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: SliderItems class.
 **/

data class SliderItems(
    @SerializedName("category_name") val categoryName: String,
    @SerializedName("category_image") val categoryImage: String
): RecyclerViewListItem {
    override fun getViewType() = CardConstants.SLIDER_ITEM
    override fun getUnique() = this
}
