package com.mnbpdx.gardenbook.data

import com.mnbpdx.gardenbook.model.Plant
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// An interface that defines what the repository needs to do
interface PlantRepository {
    fun getAllPlants(): Flow<List<Plant>>
    suspend fun getPlant(id: Int): Plant?
    suspend fun addPlant(plant: Plant)
    suspend fun updatePlant(plant: Plant)
    suspend fun deletePlant(plant: Plant)
}

// The actual repository
class PlantRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao,
) : PlantRepository {
    // TODO: ready: technically I think we could cache this in memory too?
    override fun getAllPlants(): Flow<List<Plant>> = plantDao.getAllPlants()

    override suspend fun getPlant(id: Int): Plant? = plantDao.getPlant(id)

    override suspend fun addPlant(plant: Plant) = plantDao.insertPlant(plant)

    override suspend fun updatePlant(plant: Plant) = plantDao.updatePlant(plant)

    override suspend fun deletePlant(plant: Plant) = plantDao.deletePlant(plant)
}