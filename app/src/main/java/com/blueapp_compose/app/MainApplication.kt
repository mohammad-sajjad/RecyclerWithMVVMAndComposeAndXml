package com.blueapp_compose.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


/**
 * Author: MOHAMMAD SAJJAD
 * Email: MOHAMMADSAJJAD679@gmail.com
 * Date: 23/04/25
 * Description: MainApplication class.
 */

@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}