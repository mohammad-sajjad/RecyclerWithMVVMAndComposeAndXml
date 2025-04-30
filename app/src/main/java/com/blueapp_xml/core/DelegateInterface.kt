package com.blueapp_xml.core

import android.view.ViewGroup


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: DelegateInterface class.
 */
interface DelegateInterface {
        fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder
        fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem)
}