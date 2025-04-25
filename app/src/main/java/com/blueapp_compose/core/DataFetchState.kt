package com.blueapp_compose.core

import com.blueapp_compose.error.StandardError


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: DataFetchState class.
 */
sealed class DataFetchState {
    object Idle: DataFetchState()
    data object Loading : DataFetchState()
    data object Success : DataFetchState()
    data class Error(val error: StandardError) : DataFetchState()

}