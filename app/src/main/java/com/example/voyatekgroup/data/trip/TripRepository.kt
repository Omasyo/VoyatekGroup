package com.example.voyatekgroup.data.trip

import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.network.models.CreateTripRequest
import kotlinx.coroutines.flow.Flow

interface TripRepository {
    suspend fun createTrip(
        trip: CreateTripRequest
    ) : Result<Trip>

    fun getTrip(id: String): Flow<Result<Trip>>

    fun getTrips(): Flow<Result<List<Trip>>>
}