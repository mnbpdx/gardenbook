import com.google.common.truth.Truth.assertThat
import com.mnbpdx.gardenbook.data.ApiService
import com.mnbpdx.gardenbook.data.PlantDao
import com.mnbpdx.gardenbook.model.Location
import com.mnbpdx.gardenbook.model.Plant
import io.mockk.*
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.Date

class PlantRepositoryTest {

    /* Mocks and Fakes*/
    private val mockPlantDao: PlantDao = mockk()
    private val mockApiService: ApiService = mockk()

    private val fakePlant = Plant(
        id = "1",
        name = "Rose",
        scientificName = "Rosa",
        lastWatered = Date(),
        location = Location.INDOOR,
        waterFrequency = 8,
        isSynced = false,
        description = "stuff about roses",
        imageResourceId = 42,
    )

    /* System Under Test*/
    private val plantRepository = PlantRepository(
        plantDao = mockPlantDao,
        apiService = mockApiService
    )

    @Test
    fun `getAllPlants returns flow from dao`() = runTest {
        val plantsFlow = flowOf(listOf(fakePlant))

        every { mockPlantDao.getAllPlants() } returns plantsFlow

        val result = plantRepository.getAllPlants()

        assertThat(result).isEqualTo(plantsFlow)
        verify { mockPlantDao.getAllPlants() }
    }

    @Test
    fun `addPlant inserts plant and syncs`() = runTest {
        val syncedPlant = fakePlant.copy(isSynced = true)

        coEvery { mockApiService.createPlant(fakePlant) } returns syncedPlant
        coEvery { mockPlantDao.insertPlant(any()) } just Runs

        plantRepository.addPlant(plant = fakePlant)

        coVerify {
            mockPlantDao.insertPlant(fakePlant)
            mockApiService.createPlant(fakePlant)
            mockPlantDao.insertPlant(syncedPlant)
        }
    }

    @Test
    fun `updatePlant updates plant and syncs`() = runTest {
        val syncedPlant = fakePlant.copy(isSynced = true)

        coEvery { mockApiService.updatePlant(fakePlant.id, fakePlant) } returns syncedPlant
        coEvery { mockPlantDao.updatePlant(any()) } just Runs
        coEvery { mockPlantDao.insertPlant(any()) } just Runs

        plantRepository.updatePlant(plant = fakePlant)

        coVerify {
            mockPlantDao.updatePlant(fakePlant)
            mockApiService.updatePlant(fakePlant.id, fakePlant)
            mockPlantDao.insertPlant(syncedPlant)
        }
    }

    @Test
    fun `deletePlant deletes plant from dao and api`() = runTest {
        coEvery { mockPlantDao.deletePlant(any()) } just Runs
        coEvery { mockApiService.deletePlant(any()) } just Runs

        plantRepository.deletePlant(plant = fakePlant)

        coVerify {
            mockPlantDao.deletePlant(fakePlant)
            mockApiService.deletePlant(fakePlant.id)
        }
    }

    @Test
    fun `syncPlant handles exception`() = runTest {
        coEvery { mockPlantDao.updatePlant(any()) } just Runs
        coEvery { mockApiService.updatePlant(any(), any()) } throws RuntimeException("Network error")

        plantRepository.updatePlant(plant = fakePlant)

        coVerify {
            mockPlantDao.updatePlant(fakePlant)
            mockApiService.updatePlant(fakePlant.id, fakePlant)
        }
        coVerify(exactly = 0) { mockPlantDao.insertPlant(any()) }
    }
}