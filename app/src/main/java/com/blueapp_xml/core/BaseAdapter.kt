package com.blueapp_xml.core

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.blueapp_xml.error.NoDelegateFoundException


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: BaseAdapter class.
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val items: MutableList<RecyclerViewListItem> = mutableListOf()
    protected val delegates = HashMap<Int, DelegateInterface>()
    private var viewGroup: ViewGroup? = null

    private var isPagingEnabled = false
    private var isLoadingMore = false
    private var loadMoreCallback: (() -> Unit)? = null

    override fun getItemCount(): Int = items.size

    fun getItemAtPosition(position: Int): RecyclerViewListItem = items[position]

    fun updateData(newItems: MutableList<RecyclerViewListItem>) {
        val diffCallback = BaseDiffUtils(items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(item: RecyclerViewListItem) {
        items.add(item)
        notifyItemInserted(items.lastIndex)
    }

    fun addItemAt(position: Int, item: RecyclerViewListItem) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun removeItemAt(position: Int) {
        if (position in items.indices) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun replaceItem(position: Int, newItem: RecyclerViewListItem) {
        if (position in items.indices) {
            items[position] = newItem
            notifyItemChanged(position)
        }
    }

    fun updateItemsRange(start: Int, newItems: List<RecyclerViewListItem>) {
        val safeStart = start.coerceAtLeast(0)
        val safeEnd = (safeStart + newItems.size).coerceAtMost(items.size)

        if (safeStart >= safeEnd) return

        for (i in safeStart until safeEnd) {
            items[i] = newItems[i - safeStart]
        }
        notifyItemRangeChanged(safeStart, safeEnd - safeStart)
    }


    fun clearItems() {
        val size = items.size
        items.clear()
        notifyItemRangeRemoved(0, size)
    }

    abstract fun initDelegates()

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        delegates[getItemViewType(position)]?.onBindViewHolder(holder, items[position])

        if (isPagingEnabled && !isLoadingMore && position == items.size - 1) {
            isLoadingMore = true
            loadMoreCallback?.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        viewGroup = parent
        return delegates[viewType]?.onCreateViewHolder(parent)
            ?: throw NoDelegateFoundException(viewType, this::class.java.simpleName)
    }

    fun setPagination(enabled: Boolean, loadMore: (() -> Unit)? = null) {
        isPagingEnabled = enabled
        loadMoreCallback = if (enabled) loadMore else null
    }

    fun onLoadMoreFinished() {
        isLoadingMore = false
    }

    fun getItems(): List<RecyclerViewListItem> = items
}