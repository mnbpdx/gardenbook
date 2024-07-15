package com.mnbpdx.gardenbook.data

import com.mnbpdx.gardenbook.model.Plant
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("plants")
    suspend fun getPlants(): List<Plant>

    @POST("plants")
    suspend fun createPlant(@Body plant: Plant): Plant

    @PUT("plants/{id}")
    suspend fun updatePlant(@Path("id") id: String, @Body plant: Plant): Plant

    @DELETE("plants/{id}")
    suspend fun deletePlant(@Path("id") id: String)
}
