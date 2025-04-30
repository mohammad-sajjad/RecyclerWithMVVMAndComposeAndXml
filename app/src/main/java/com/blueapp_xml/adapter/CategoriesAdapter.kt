package com.blueapp_xml.adapter

import android.view.ViewGroup
import android.widget.ImageView
import com.blueapp_xml.R
import com.blueapp_xml.utils.CardConstants
import com.blueapp_xml.core.BaseAdapter
import com.blueapp_xml.core.BaseDelegate
import com.blueapp_xml.core.BaseViewHolder
import com.blueapp_xml.utils.Extensions.inflate
import com.blueapp_xml.core.RecyclerViewListItem
import com.blueapp_xml.models.CategoryItems
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: CategoriesAdapter class.
 */
class CategoriesAdapter: BaseAdapter() {

    init {
        initDelegates()
    }

    override fun initDelegates() {
        delegates[CardConstants.CATEGORY_ITEM] = CategoriesItemDelegate()
    }
}

class CategoriesItemDelegate : BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = CategoriesItemViewHolder(parent)
}

class CategoriesItemViewHolder(parent: ViewGroup): BaseViewHolder(parent.inflate(R.layout.category_item_layout)) {
    private val image = itemView.findViewById<ImageView>(R.id.category_image)

    override fun bindView(item: RecyclerViewListItem) {
        item as CategoryItems
        Glide.with(itemView.context)
            .load(item.categoryImage)
            .transform(RoundedCorners(60))
            .into(image)

    }

}