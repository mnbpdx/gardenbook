package com.mnbpdx.gardenbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.mnbpdx.gardenbook.ui.Destination
import com.mnbpdx.gardenbook.ui.NavigationViewModel
import com.mnbpdx.gardenbook.ui.screens.addplant.AddPlantScreen
import com.mnbpdx.gardenbook.ui.screens.addplant.AddPlantViewModel
import com.mnbpdx.gardenbook.ui.screens.detail.DetailScreen
import com.mnbpdx.gardenbook.ui.screens.detail.DetailScreenViewModel
import com.mnbpdx.gardenbook.ui.screens.home.HomeScreen
import com.mnbpdx.gardenbook.ui.screens.home.HomeScreenViewModel
import com.mnbpdx.gardenbook.ui.screens.loading.LoadingScreen
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
                // here we're using a when statement to determine which screen to show
                //  based on a destination value in the navigation view model
                when (val destination = navigationViewModel.destination.value) {
                    is Destination.LoadingScreen -> LoadingScreen()

                    is Destination.HomeScreen -> {
                        HomeScreen(
                            onPlantCardPress = { id ->
                                navigationViewModel.navigateToDetailScreen(id)
                            },
                            onAddPlantPress = navigationViewModel::navigateToAddPlantScreen,
                        )
                    }

                    is Destination.DetailScreen -> {
                        DetailScreen(
                            onBackPress = navigationViewModel::navigateToHomeScreen,
                            id = destination.id,
                        )
                    }

                    is Destination.AddPlantScreen -> {
                        AddPlantScreen(
                            onBackPress = navigationViewModel::navigateToHomeScreen,
                        )
                    }
                }

            }
        }
    }
}