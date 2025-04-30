package com.blueapp_xml.core


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: BaseDelegate class.
 */
abstract class BaseDelegate : DelegateInterface {

    override fun onBindViewHolder(holder: BaseViewHolder, item: RecyclerViewListItem) {
        holder.bindView(item)
    }
}