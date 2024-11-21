package com.example.voyatekgroup.ui.features.plantrip

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.voyatekgroup.data.trip.TripRepository
import com.example.voyatekgroup.models.City
import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.network.models.CreateTripRequest
import com.example.voyatekgroup.ui.components.SelectedDate
import com.example.voyatekgroup.ui.components.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

sealed interface CreateTripState {
    data object Idle : CreateTripState
    data object Submitting : CreateTripState
    data class Submitted(val id: String) : CreateTripState
    data class Error(val message: String) : CreateTripState
}

@HiltViewModel
class PlanTripViewModel @Inject constructor(
    private val repository: TripRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    private val allCities = Cities
    val cities = _searchQuery.map { query ->
        allCities.filter { city ->
            city.city.contains(query) || city.location.contains(query)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), allCities)

    fun changeSearchQuery(value: String) {
        _searchQuery.value = value
    }

    var selectedCity by mutableStateOf<City?>(null)
        private set

    fun selectCity(city: City) {
        selectedCity = city
    }

    var selectedDate by mutableStateOf(SelectedDate.Start)
        private set

    fun changeSelectedDate(selectedDate: SelectedDate) {
        this.selectedDate = selectedDate
    }

    var dateRange by mutableStateOf<Pair<LocalDate?, LocalDate?>>(Pair(null, null))
        private set

    fun setDate(date: LocalDate) {
        println("Trying to set date $date $selectedDate is $selectedDate")
        if (selectedDate.isStart && dateRange.second != null && date > dateRange.second) return
        if (selectedDate.isEnd && date < dateRange.first) return

        dateRange =
            if (selectedDate.isStart) {
                selectedDate = SelectedDate.End
                dateRange.copy(first = date)
            } else dateRange.copy(second = date)
    }

    var tripName by mutableStateOf("")
        private set

    fun changeTripName(value: String) {
        tripName = value
    }

    var travelStyle by mutableStateOf<String?>(null)
        private set

    fun changeTravelStyle(value: String) {
        travelStyle = value
    }

    var description by mutableStateOf("")
        private set

    fun changeDescription(value: String) {
        description = value
    }

    private val _createTripState = MutableStateFlow<CreateTripState>(CreateTripState.Idle)
    val createTripState = _createTripState.asStateFlow()

    private val refreshTripsEvent = MutableStateFlow(Any())

    @OptIn(ExperimentalCoroutinesApi::class)
    val trips: StateFlow<UiState<List<Trip>>> =
        refreshTripsEvent.flatMapLatest {
            repository.getTrips().map { result ->
                result.fold(
                    onSuccess = { UiState.Success(it) },
                    onFailure = { UiState.Error(it.message ?: "An Error Occurred") }
                )
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState.Loading)

    fun createTrip(): Job {
        if (travelStyle == null || selectedCity == null ||
            dateRange.first == null || dateRange.second == null
        ) {
            return viewModelScope.launch {
                _createTripState.emit(
                    CreateTripState.Error(
                        "Please complete your trip details"
                    )
                )
            }

        }

        _createTripState.value = CreateTripState.Submitting
        return viewModelScope.launch {
            val result = repository.createTrip(
                CreateTripRequest(
                    name = tripName,
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/7b/Fair_weather_clouds_on_Obudu_mountains.jpg",
                    style = travelStyle!!,
                    description = description,
                    location = selectedCity!!.city,
                    startDate = dateRange.first!!,
                    endDate = dateRange.second!!

                )
            )
            _createTripState.value = result.fold(
                onSuccess = {
                    CreateTripState.Submitted(it.id)
                },
                onFailure = { CreateTripState.Error(it.message ?: "An Error Occurred") }
            )
            result.onSuccess {
                refreshTrips()
            }
        }
    }

    fun clearCreateTripState() {
        _createTripState.value = CreateTripState.Idle
    }


    fun clearState() {
        selectedCity = null
        dateRange = Pair(null, null)
        tripName = ""
        travelStyle = null
        description = ""
        _createTripState.value = CreateTripState.Idle
    }

    private fun refreshTrips() {
        refreshTripsEvent.tryEmit(Any())
    }
}

val Cities = listOf(
    City("Utopia, Bliss", "San Junipero", "NG"),
    City("Enugu, Nigeria", "Enugu", "NG"),
    City("Lagos, Nigeria", "Muritala Muhammed", "NG"),
    City("Lagos, Nigeria", "National Stadium", "NG"),
    City("Someplace, Somewhere", "Heaven", "NG"),
)