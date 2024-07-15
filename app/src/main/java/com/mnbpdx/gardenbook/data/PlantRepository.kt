import com.mnbpdx.gardenbook.data.ApiService
import com.mnbpdx.gardenbook.data.PlantDao
import com.mnbpdx.gardenbook.model.Plant

class PlantRepository(
    private val plantDao: PlantDao,
    private val apiService: ApiService
) {
    fun getAllPlants() = plantDao.getAllPlants()

    suspend fun addPlant(plant: Plant) {
        plantDao.insertPlant(plant)
        syncPlant(plant)
    }

    suspend fun updatePlant(plant: Plant) {
        plantDao.updatePlant(plant)
        syncPlant(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        plantDao.deletePlant(plant)
        apiService.deletePlant(plant.id)
    }

    private suspend fun syncPlant(plant: Plant) {
        try {
            val syncedPlant = if (plant.id.isEmpty()) {
                apiService.createPlant(plant)
            } else {
                apiService.updatePlant(plant.id, plant)
            }
            plantDao.insertPlant(syncedPlant.copy(isSynced = true))
        } catch (e: Exception) {
            // Handle error, maybe retry later
        }
    }
}