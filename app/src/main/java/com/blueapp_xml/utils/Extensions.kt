package com.blueapp_xml.utils

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: Extensions class.
 */
object Extensions {
    fun ViewGroup.inflate(resId: Int): View {
        return LayoutInflater.from(context).inflate(resId, this, false)
    }
    fun Activity.shortToast(msg: String) {
        val toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast.show()
    }

    fun ImageView.loadImage(
        context: Context,
        imageUrl: String?,
        placeholderResId: Int = 0,
        cornerRadiusDp: Int,
        widthPx: Int,
        heightPx: Int,
    ) {
        if (imageUrl != null) {
            val radius = context.resources.displayMetrics.density * cornerRadiusDp // Convert DP to pixels
            val requestOptions = RequestOptions()
                .placeholder(placeholderResId)
                .transform(RoundedCorners(radius.toInt()))

            Glide.with(context)
                .load(imageUrl)
                .override(widthPx, heightPx) // Fixed pixel dimensions
                .apply(requestOptions)
                .into(this)
        } else if (placeholderResId != 0) {
            Glide.with(context)
                .load(placeholderResId)
                .apply(RequestOptions().transform(RoundedCorners(cornerRadiusDp)))
                .centerCrop()
                .into(this)
        }
    }
}