package com.blueapp_compose.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: BaseViewHolder class.
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindView(item: RecyclerViewListItem)
}