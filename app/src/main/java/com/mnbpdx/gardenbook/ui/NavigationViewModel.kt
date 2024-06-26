package com.mnbpdx.gardenbook.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

    private val _destination: MutableState<Destination> = mutableStateOf(Destination.HomeScreen)
    val destination: State<Destination> = _destination

    fun navigateToDetailScreen(name: String) {
        _destination.value = Destination.DetailScreen(name)
    }

    fun navigateToHomeScreen() {
        _destination.value = Destination.HomeScreen
    }

}

sealed interface Destination {
    data object LoadingScreen : Destination
    data object HomeScreen : Destination
    data class DetailScreen(
        val plantName: String
    ) : Destination
}