package com.mnbpdx.gardenbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.home.HomeScreen
import com.mnbpdx.gardenbook.ui.loading.LoadingScreen
import com.mnbpdx.gardenbook.ui.NavigationViewModel
import com.mnbpdx.gardenbook.ui.detail.DetailScreen
import com.mnbpdx.gardenbook.ui.theme.GardenBookTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val navigationViewModel: NavigationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Composables go here -->
            // Composables are functions, but start with capital
            // letters, like Greeting, below.
            GardenBookTheme {
//                HomeScreen(
//                    onPlantCardPress = navigationViewModel::navigateToDetailScreen()
//                )

                when (navigationViewModel.destination.value) {
                    is Destination.LoadingScreen -> LoadingScreen()
                    is Destination.HomeScreen -> {
                        HomeScreen(
                            onPlantCardPress = navigationViewModel::navigateToDetailScreen
                        )
                    }
                    is Destination.DetailScreen -> {
//                        DetailScreen(
//                            plant =
//                        )
                    }
                }

            }
        }
    }


}