package com.example.voyatekgroup.data.trip

import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.network.models.CreateTripRequest
import com.example.voyatekgroup.network.trip.TripNetworkSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(
    private val networkSource: TripNetworkSource,
    private val dispatcher: CoroutineDispatcher
) : TripRepository {
    override suspend fun createTrip(trip: CreateTripRequest): Result<Trip> {
        return networkSource.createTrip(trip)
    }

    override fun getTrip(id: String): Flow<Result<Trip>> = flow {
        emit(networkSource.getTrip(id))
    }.flowOn(dispatcher)

    override fun getTrips(): Flow<Result<List<Trip>>> {
        return networkSource::getTrips.asFlow().flowOn(dispatcher)
    }
}