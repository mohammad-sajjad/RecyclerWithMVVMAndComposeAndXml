package com.blueapp_compose.core

import androidx.recyclerview.widget.DiffUtil


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: BaseDiffUtils class.
 */
class BaseDiffUtils(
    private val oldItems: MutableList<RecyclerViewListItem>,
    private val newItems: MutableList<RecyclerViewListItem>
) :
    DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique()

    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.getViewType() == newItem.getViewType() && oldItem.getUnique() == newItem.getUnique()
    }
}