package com.mnbpdx.gardenbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mnbpdx.gardenbook.ui.LoadingScreen
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Composables go here -->
            // Composables are functions, but start with capital
            // letters, like Greeting, below.
            GardenBookTheme {
                LoadingScreen()
            }
        }
    }
}