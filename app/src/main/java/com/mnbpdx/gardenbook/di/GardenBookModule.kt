package com.mnbpdx.gardenbook.di

import android.content.Context
import androidx.room.Room
import com.mnbpdx.gardenbook.data.AppDatabase
import com.mnbpdx.gardenbook.data.PlantDao
import com.mnbpdx.gardenbook.data.PlantRepository
import com.mnbpdx.gardenbook.data.PlantRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GardenBookBindingsModule {

    @Binds
    @Singleton
    abstract fun bindPlantRepository(
        plantRepositoryImpl: PlantRepositoryImpl
    ): PlantRepository
}

@Module
@InstallIn(SingletonComponent::class)
object GardenBookProvidersModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "garden_book_database"
        ).build()

    @Provides
    fun providePlantDao(database: AppDatabase): PlantDao {
        return database.plantDao()
    }
}