package com.example.voyatekgroup.network.trip

import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.network.models.CreateTripRequest

interface TripNetworkSource {
    suspend fun createTrip(
        trip: CreateTripRequest
    ) : Result<Trip>

    suspend fun getTrip(id: String): Result<Trip>

    suspend fun getTrips(): Result<List<Trip>>
}