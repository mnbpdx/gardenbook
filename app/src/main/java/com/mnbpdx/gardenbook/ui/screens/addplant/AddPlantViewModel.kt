package com.mnbpdx.gardenbook.ui.screens.addplant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnbpdx.gardenbook.R
import com.mnbpdx.gardenbook.data.PlantRepository
import com.mnbpdx.gardenbook.model.Location
import com.mnbpdx.gardenbook.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlantViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {

    // Calls the repository to add a plant to the database
    fun addPlant(
        name: String,
        description: String
    ) {
        viewModelScope.launch {
            plantRepository.addPlant(
                Plant(
                    name = name,
                    scientificName = "demo",
                    description = description,
                    location = Location.INDOOR,
                    waterFrequency = 42,
                    imageResourceId = R.drawable.leaf,
                    daysSinceLastWatered = 1,
                )
            )
        }
    }
}
