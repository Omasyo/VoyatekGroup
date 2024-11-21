package com.example.voyatekgroup.ui.features.tripdetails

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.voyatekgroup.TripDetails
import com.example.voyatekgroup.data.trip.TripRepository
import com.example.voyatekgroup.models.Itinerary
import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.ui.components.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TripDetailsViewModel @Inject constructor(
    private val repository: TripRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val route = savedStateHandle.toRoute<TripDetails>()


    private val refreshRoomEvent = MutableStateFlow(Any())

    @OptIn(ExperimentalCoroutinesApi::class)
    val trip: StateFlow<UiState<Trip>> =
        refreshRoomEvent.flatMapLatest {
            repository.getTrip(route.id)
                .map { result ->
                    result.fold(
                        onSuccess = { UiState.Success(it) },
                        onFailure = { UiState.Error(it.message ?: "An Error Occurred") }
                    )
                }
        }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)

    var itineraries by mutableStateOf(listOf<Itinerary>())
        private set

    fun addItinerary(itinerary: Itinerary) {
        itineraries += itinerary
    }

    fun removeItinerary(itinerary: Itinerary) {
        itineraries -= itinerary
    }

    fun refresh() {
        refreshRoomEvent.tryEmit(Any())
    }
}