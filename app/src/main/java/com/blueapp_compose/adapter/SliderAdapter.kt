package com.blueapp_compose.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.blueapp_compose.CardConstants
import com.blueapp_compose.R
import com.blueapp_compose.core.BaseAdapter
import com.blueapp_compose.core.BaseDelegate
import com.blueapp_compose.core.BaseViewHolder
import com.blueapp_compose.core.Extensions.inflate
import com.blueapp_compose.core.Extensions.loadImage
import com.blueapp_compose.core.RecyclerViewListItem
import com.blueapp_compose.models.SliderItems
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 25/04/25
 * Description: SliderAdapter class.
 */
class SliderAdapter: BaseAdapter() {
    init {
        initDelegates()
    }
    override fun initDelegates() {
        delegates[CardConstants.SLIDER_ITEM] = SliderDelegate()
    }
}

class SliderDelegate : BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = SliderViewHolder(parent)

}

class SliderViewHolder(parent: ViewGroup) : BaseViewHolder(parent.inflate(R.layout.slider_item_layout)) {
    val image = itemView.findViewById<ImageView>(R.id.slider_image)
    override fun bindView(item: RecyclerViewListItem) {
        item as SliderItems
        val screenWidth = itemView.context.resources.displayMetrics.widthPixels
        Glide.with(itemView.context)
            .load(item.categoryImage)
            .transform(RoundedCorners(60))
            .into(image)
//        image.loadImage(context = itemView.context, imageUrl = item.categoryImage, placeholderResId = R.drawable.image_place_holder, cornerRadiusDp = 14, widthPx = screenWidth, heightPx = 250)
    }

}