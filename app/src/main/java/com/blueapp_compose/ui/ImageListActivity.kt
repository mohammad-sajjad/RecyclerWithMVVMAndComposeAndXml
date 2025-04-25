package com.blueapp_compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.blueapp_compose.theme.BlueAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlueAppTheme {
                Scaffold { paddingValues->
                    ProductsScreen(Modifier.padding(paddingValues))
                }
            }
        }
    }
}
