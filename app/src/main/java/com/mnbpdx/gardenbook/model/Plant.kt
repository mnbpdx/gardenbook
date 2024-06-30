package com.mnbpdx.gardenbook.model

import androidx.annotation.DrawableRes
import com.mnbpdx.gardenbook.R

// Created by Claude
// TODO: review this ai generated demo model

data class Plant(
    val id: String,
    val name: String,
    val scientificName: String,
    val description: String,
    val location: Location,
    val waterFrequency: Int, // in days
    @DrawableRes val imageResourceId: Int
)

enum class Location {
    INDOOR, OUTDOOR
}

val plantList : List<Plant> = listOf(
    Plant(
        id = "1",
        name = "Pothos",
        scientificName = "Epipremnum aureum",
        description = "A hardy, trailing plant with heart-shaped leaves. Great for beginners.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "2",
        name = "Snake Plant",
        scientificName = "Sansevieria trifasciata",
        description = "An upright plant with stiff, sword-like leaves. Tolerates low light and irregular watering.",
        location = Location.INDOOR,
        waterFrequency = 14,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "3",
        name = "Fiddle Leaf Fig",
        scientificName = "Ficus lyrata",
        description = "A popular indoor tree with large, violin-shaped leaves. Requires consistent care.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "4",
        name = "Spider Plant",
        scientificName = "Chlorophytum comosum",
        description = "A fast-growing plant that produces plantlets. Great for hanging baskets.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "5",
        name = "Peace Lily",
        scientificName = "Spathiphyllum",
        description = "An elegant plant with dark leaves and white flowers. Good at cleaning indoor air.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "6",
        name = "Monstera",
        scientificName = "Monstera deliciosa",
        description = "A trendy plant with large, split leaves. Can grow quite large over time.",
        location = Location.OUTDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "7",
        name = "Aloe Vera",
        scientificName = "Aloe barbadensis miller",
        description = "A succulent plant known for its medicinal properties. Requires very little water.",
        location = Location.OUTDOOR,
        waterFrequency = 14,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    ),
    Plant(
        id = "8",
        name = "ZZ Plant",
        scientificName = "Zamioculcas zamiifolia",
        description = "A hardy plant with glossy leaves. Can tolerate low light and neglect.",
        location = Location.OUTDOOR,
        waterFrequency = 14,
        imageResourceId = R.drawable.leaf // placeholder, replace with actual image
    )
)

val plantNames = plantList.map { it.name }