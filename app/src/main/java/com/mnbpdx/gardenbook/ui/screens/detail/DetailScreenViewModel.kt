package com.mnbpdx.gardenbook.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnbpdx.gardenbook.data.PlantRepository
import com.mnbpdx.gardenbook.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {
    private val plant: MutableStateFlow<Plant?> = MutableStateFlow(null)

    val screenState: StateFlow<ScreenState> = plant.map { currentPlant ->
        when (currentPlant) {
            null -> ScreenState.Loading
            else -> ScreenState.Loaded(currentPlant)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ScreenState.Loading
    )

    fun loadPlant(id: Int) {
        viewModelScope.launch {
            plant.value = plantRepository.getPlant(id)
        }
    }

    sealed interface ScreenState {
        object Loading : ScreenState
        data class Loaded(val plant: Plant) : ScreenState
        object Error : ScreenState
    }
}