package com.mnbpdx.gardenbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mnbpdx.gardenbook.R

// TODO: review this ai generated demo model
@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val scientificName: String,
    val description: String,
    val location: Location,
    val waterFrequency: Int,
    val imageResourceId: Int,
    val daysSinceLastWatered: Int,
    @ColumnInfo(name = "is_synced") val isSynced: Boolean = false
)

enum class Location {
    INDOOR, OUTDOOR
}

val plantList : List<Plant> = listOf(
    Plant(
        name = "Pothos",
        scientificName = "Epipremnum aureum",
        description = "A hardy, trailing plant with heart-shaped leaves. Great for beginners.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf, 
        daysSinceLastWatered = 0,
    ),
    Plant(
        name = "Snake Plant",
        scientificName = "Sansevieria trifasciata",
        description = "An upright plant with stiff, sword-like leaves. Tolerates low light and irregular watering.",
        location = Location.INDOOR,
        waterFrequency = 14,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    ),
    Plant(
        name = "Fiddle Leaf Fig",
        scientificName = "Ficus lyrata",
        description = "A popular indoor tree with large, violin-shaped leaves. Requires consistent care.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    ),
    Plant(
        name = "Spider Plant",
        scientificName = "Chlorophytum comosum",
        description = "A fast-growing plant that produces plantlets. Great for hanging baskets.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    ),
    Plant(
        name = "Peace Lily",
        scientificName = "Spathiphyllum",
        description = "An elegant plant with dark leaves and white flowers. Good at cleaning indoor air.",
        location = Location.INDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    ),
    Plant(
        name = "Monstera",
        scientificName = "Monstera deliciosa",
        description = "A trendy plant with large, split leaves. Can grow quite large over time.",
        location = Location.OUTDOOR,
        waterFrequency = 7,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    ),
    Plant(
        name = "Aloe Vera",
        scientificName = "Aloe barbadensis miller",
        description = "A succulent plant known for its medicinal properties. Requires very little water.",
        location = Location.OUTDOOR,
        waterFrequency = 14,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    ),
    Plant(
        name = "ZZ Plant",
        scientificName = "Zamioculcas zamiifolia",
        description = "A hardy plant with glossy leaves. Can tolerate low light and neglect.",
        location = Location.OUTDOOR,
        waterFrequency = 14,
        imageResourceId = R.drawable.leaf,
        daysSinceLastWatered = 2,
    )
)