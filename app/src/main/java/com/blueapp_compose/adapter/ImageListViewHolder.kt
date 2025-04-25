package com.blueapp_compose.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blueapp_compose.R
import com.blueapp_compose.core.BaseViewHolder
import com.blueapp_compose.core.Extensions.inflate
import com.blueapp_compose.core.Extensions.loadImage
import com.blueapp_compose.core.RecyclerViewListItem
import com.blueapp_compose.models.ImagesItems
import com.bumptech.glide.Glide


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ImageListViewHolder class.
 */
class ImageListViewHolder(parent: ViewGroup) : BaseViewHolder(parent.inflate(R.layout.image_list_item_layout)) {
    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleTV = itemView.findViewById<TextView>(R.id.title_tv)
    private val detailsTv = itemView.findViewById<TextView>(R.id.details_tv)
    override fun bindView(item: RecyclerViewListItem) {
        item as ImagesItems
        titleTV.text = item.title
        detailsTv.text = item.details

        imageView.loadImage(context = itemView.context, imageUrl = item.image, placeholderResId = R.drawable.image_place_holder, cornerRadiusDp = 14, widthPx = 200, heightPx = 250)

    }
}