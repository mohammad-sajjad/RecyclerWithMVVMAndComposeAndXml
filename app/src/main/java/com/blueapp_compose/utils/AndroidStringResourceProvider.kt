package com.blueapp_xml.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 30/04/25
 * Description: AndroidStringResourceProvider class.
 */
class AndroidStringResourceProvider @Inject constructor(@ApplicationContext private val context: Context ) : StringResourceProvider {
    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}