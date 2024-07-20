import com.google.common.truth.Truth.assertThat
import com.mnbpdx.gardenbook.data.PlantDao
import com.mnbpdx.gardenbook.data.PlantRepositoryImpl
import com.mnbpdx.gardenbook.model.Location
import com.mnbpdx.gardenbook.model.Plant
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PlantRepositoryTest {

    /* Mocks and Fakes*/
    private val mockPlantDao: PlantDao = mockk()

    private val fakePlant = Plant(
        name = "Rose",
        scientificName = "Rosa",
        daysSinceLastWatered = 7,
        location = Location.INDOOR,
        waterFrequency = 8,
        isSynced = false,
        description = "stuff about roses",
        imageResourceId = 42,
    )

    /* System Under Test*/
    private val plantRepository = PlantRepositoryImpl(
        plantDao = mockPlantDao,
    )

    /* Tests */
    @Test
    fun `when getAllPlants() is called, it returns flow from dao`() = runTest {
        // Given
        val plantsFlow = flowOf(listOf(fakePlant))
        every { mockPlantDao.getAllPlants() } returns plantsFlow

        // When
        val result = plantRepository.getAllPlants()

        // Then
        assertThat(result).isEqualTo(plantsFlow)
    }

    @Test
    fun `when addPlant() is called, it inserts plant into dao`() = runTest {
        // Given
        coEvery { mockPlantDao.insertPlant(plant = fakePlant) } just runs

        // When
        plantRepository.addPlant(plant = fakePlant)

        // Then
        coVerify { mockPlantDao.insertPlant(plant = fakePlant) }
    }

    @Test
    fun `when updatePlant() is called, it updates plant in dao`() = runTest {
        // Given
        coEvery { mockPlantDao.updatePlant(any()) } just runs

        // When
        plantRepository.updatePlant(plant = fakePlant)

        // Then
        coVerify { mockPlantDao.updatePlant(plant = fakePlant) }
    }

    @Test
    fun `when deletePlant() is called, it deletes plant from dao`() = runTest {
        // Given
        coEvery { mockPlantDao.deletePlant(any()) } just runs

        // When
        plantRepository.deletePlant(plant = fakePlant)

        // Then
        coVerify { mockPlantDao.deletePlant(fakePlant) }
    }
}