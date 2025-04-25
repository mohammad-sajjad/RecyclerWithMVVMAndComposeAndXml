package com.blueapp_compose.adapter

import com.blueapp_compose.CardConstants
import com.blueapp_compose.core.BaseAdapter


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: ImageListAdapter class.
 */
class ImageListAdapter: BaseAdapter() {
    init {
        initDelegates()
    }

    override fun initDelegates() {
        delegates[CardConstants.IMAGE_LIST_ITEM] = ImageListDelegate()
    }

}