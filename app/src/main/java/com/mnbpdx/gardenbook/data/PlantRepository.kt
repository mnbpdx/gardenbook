import com.mnbpdx.gardenbook.data.PlantDao
import com.mnbpdx.gardenbook.model.Plant
import kotlinx.coroutines.flow.Flow

class PlantRepository(
    private val plantDao: PlantDao,
) {
    fun getAllPlants(): Flow<List<Plant>> = plantDao.getAllPlants()

    suspend fun addPlant(plant: Plant) {
        plantDao.insertPlant(plant)
    }

    suspend fun updatePlant(plant: Plant) {
        plantDao.updatePlant(plant)
    }

    suspend fun deletePlant(plant: Plant) {
        plantDao.deletePlant(plant)
    }
}