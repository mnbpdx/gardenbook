package com.mnbpdx.gardenbook.data

import androidx.databinding.adapters.Converters
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.mnbpdx.gardenbook.model.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAllPlants(): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: Plant)

    @Update
    suspend fun updatePlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)

    @Query("SELECT * FROM plants WHERE is_synced = 0")
    suspend fun getUnsyncedPlants(): List<Plant>
}

@Database(entities = [Plant::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}