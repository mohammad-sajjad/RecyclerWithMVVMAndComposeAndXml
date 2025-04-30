package com.blueapp_xml.adapter

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.blueapp_xml.utils.CardConstants
import com.blueapp_xml.R
import com.blueapp_xml.core.BaseAdapter
import com.blueapp_xml.core.BaseDelegate
import com.blueapp_xml.core.BaseViewHolder
import com.blueapp_xml.utils.Extensions.inflate
import com.blueapp_xml.utils.Extensions.loadImage
import com.blueapp_xml.core.RecyclerViewListItem
import com.blueapp_xml.models.ProductItems


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: ProductsAdapter class.
 */
class ProductsAdapter: BaseAdapter() {
    init {
        initDelegates()
    }
    override fun initDelegates() {
        delegates[CardConstants.PRODUCT_ITEMS] = ProductItemDelegate()
    }
}

class ProductItemDelegate: BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = ProductItemViewHolder(parent)
}

class ProductItemViewHolder(parent: ViewGroup): BaseViewHolder(parent.inflate(R.layout.product_item_layout)) {
    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleTV = itemView.findViewById<TextView>(R.id.title_tv)
    private val detailsTv = itemView.findViewById<TextView>(R.id.details_tv)

    override fun bindView(item: RecyclerViewListItem) {
        item as ProductItems
        titleTV.text = item.title
        detailsTv.text = item.description

        imageView.loadImage(context = itemView.context, imageUrl = item.image, placeholderResId = R.drawable.image_place_holder, cornerRadiusDp = 14, widthPx = 200, heightPx = 250)

    }

}