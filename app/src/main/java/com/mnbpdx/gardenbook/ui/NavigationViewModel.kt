package com.mnbpdx.gardenbook.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mnbpdx.gardenbook.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val _destination: MutableState<Destination> = mutableStateOf(Destination.HomeScreen)
    val destination: State<Destination> = _destination

    fun navigateToDetailScreen(id: Int) {
        _destination.value = Destination.DetailScreen(id)
    }

    fun navigateToHomeScreen() {
        _destination.value = Destination.HomeScreen
    }

    fun navigateToAddPlantScreen() {
        _destination.value = Destination.AddPlantScreen
    }

}

sealed interface Destination {
    data object LoadingScreen : Destination
    data object HomeScreen : Destination
    data object AddPlantScreen : Destination

    data class DetailScreen(
        val id: Int
    ) : Destination
}