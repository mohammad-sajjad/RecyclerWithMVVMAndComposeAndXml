package com.blueapp_compose.adapter

import android.view.ViewGroup
import com.blueapp_compose.core.BaseDelegate
import com.blueapp_compose.core.BaseViewHolder
import com.blueapp_compose.core.DelegateInterface
import com.blueapp_compose.core.RecyclerViewListItem


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ImageListDelegate class.
 */
class ImageListDelegate: BaseDelegate() {
    override fun onCreateViewHolder(parent: ViewGroup) = ImageListViewHolder(parent)

}