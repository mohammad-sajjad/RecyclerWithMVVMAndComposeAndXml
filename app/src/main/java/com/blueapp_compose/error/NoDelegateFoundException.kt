package com.blueapp_compose.error


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 24/04/25
 * Description: NoDelegateFoundException class.
 */
class NoDelegateFoundException(private val item: Int, private val errorClass: String) : Exception() {
    override val message: String?
        get() = "No delegate found for the view type : $item in $errorClass"
}