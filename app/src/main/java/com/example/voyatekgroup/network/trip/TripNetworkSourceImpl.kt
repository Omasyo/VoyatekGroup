package com.example.voyatekgroup.network.trip

import android.util.Log
import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.network.models.CreateTripRequest
import com.example.voyatekgroup.network.models.ErrorResponse
import com.example.voyatekgroup.ui.components.UiState
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.isSuccess
import javax.inject.Inject

internal class TripNetworkSourceImpl @Inject constructor(
    private val client: HttpClient
) : TripNetworkSource {

    val tag: String = this::class.simpleName!!

    override suspend fun createTrip(trip: CreateTripRequest): Result<Trip> = makeRequest {
        client.post("/trips") {
            setBody(trip)
        }
    }

    override suspend fun getTrip(id: String): Result<Trip> = makeRequest {
        client.get("/trips/$id")
    }

    override suspend fun getTrips(): Result<List<Trip>> = makeRequest {
        client.get("/trips")
    }

    private suspend inline fun <reified T> makeRequest(
        exec: () -> HttpResponse
    ): Result<T> {
        return try {
            val response = exec()

            Log.i(tag, "Made request ${response.request.method} ${response.request.url}")
            Log.i(tag, "Received: $response ${response.status}")
            Log.i(tag, "Received: $response ${response.bodyAsText()}")
            if (response.status.isSuccess()) {
                val content: T = response.body()
                Result.success(content)
            } else {
                Log.e(tag, "Response error - ${response.bodyAsText()}")
                val error: ErrorResponse = response.body()

                Result.failure(error.error)

            }
        } catch (e: Exception) {
            Log.e(tag, "Exception ${e.message}")
            Result.failure(Exception("An Error Occurred", e))
        }
    }
}