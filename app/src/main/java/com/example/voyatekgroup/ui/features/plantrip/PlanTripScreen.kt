package com.example.voyatekgroup.ui.features.plantrip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.voyatekgroup.R
import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.ui.components.SelectedDate
import com.example.voyatekgroup.ui.components.TripCard
import com.example.voyatekgroup.ui.components.UiState
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.formatFullDate
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun PlanTripRoute(
    modifier: Modifier = Modifier,
    onSelectCityTap: () -> Unit,
    onSelectDateTap: () -> Unit,
    onTripDetailsTap: (String) -> Unit,
    onBackTap: () -> Unit,
    viewModel: PlanTripViewModel
) {
    PlanTripScreen(
        modifier = modifier,
        destination = viewModel.selectedCity?.city,
        dateRange = viewModel.dateRange,
        trips = viewModel.trips.collectAsStateWithLifecycle().value,
        onSelectCityTap = onSelectCityTap,
        onSelectDateTap = { selectedDate ->
            viewModel.changeSelectedDate(selectedDate)
            onSelectDateTap()
        },
        onTripDetailsTap = onTripDetailsTap,
        newTripName = viewModel.tripName,
        onNameChange = viewModel::changeTripName,
        travelStyle = viewModel.travelStyle,
        onTravelStyleChange = viewModel::changeTravelStyle,
        description = viewModel.description,
        onDescriptionChange = viewModel::changeDescription,
        onSubmit = viewModel::createTrip,
        createTripState = viewModel.createTripState.collectAsStateWithLifecycle().value,
        onCreateTripError = viewModel::clearCreateTripState,
        onCreateTripSuccess = {
            viewModel.clearState()
            onTripDetailsTap(it)
        },
        onBackTap = onBackTap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen(
    modifier: Modifier = Modifier,
    destination: String?,
    dateRange: Pair<LocalDate?, LocalDate?>,
    trips: UiState<List<Trip>>,
    onSelectCityTap: () -> Unit,
    onSelectDateTap: (SelectedDate) -> Unit,
    onTripDetailsTap: (String) -> Unit,

    newTripName: String,
    onNameChange: (String) -> Unit,
    travelStyle: String?,
    onTravelStyleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onSubmit: () -> Unit,
    createTripState: CreateTripState,
    onCreateTripError: () -> Unit,
    onCreateTripSuccess: (String) -> Unit,

    onBackTap: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("Plan a Trip")
                },
                navigationIcon = {
                    IconButton(onClick = onBackTap) {
                        Icon(painterResource(R.drawable.arrow_left), null)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            item {
                Column {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(horizontal = 16f.dp, vertical = 24f.dp)
                    ) {
                        Text(
                            text = "Plan Your Dream Trip in Minutes",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(Modifier.height(8f.dp))
                        Text(
                            text = "Build, personalize, and optimize your itineraries with our trip planner. Perfect for getaways, remote workcations, and any spontaneous escapade.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painterResource(R.drawable.hotel_alt),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 400f.dp)
                        )
                        CreateTrip(
                            modifier = Modifier
                                .padding(top = 56f.dp)
                                .padding(24f.dp)
                                .widthIn(max = 400f.dp),
                            destination = destination,
                            startDate = dateRange.first,
                            endDate = dateRange.second,
                            onSelectCityTap = onSelectCityTap,
                            onSelectDateTap = onSelectDateTap,
                            onCreateTripTap = {
                                showBottomSheet = true
                            }
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16f.dp, vertical = 24f.dp)
                    ) {
                        Text(
                            text = "Your Trips",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(8f.dp))
                        Text(
                            text = "Your trip itineraries and  planned trips are placed here",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16f.dp)
                            .fillMaxWidth()
                            .clip(MaterialTheme.shapes.large)
                            .clickable { }
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(8f.dp)
                            .clip(MaterialTheme.shapes.large)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(8f.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Planned Trips",
                            style = MaterialTheme.typography.titleSmall
                        )
                        Icon(painterResource(R.drawable.caret_down), null)
                    }
                    Spacer(Modifier.height(24f.dp))
                }
            }
            when (trips) {
                is UiState.Error -> {}
                UiState.Loading -> {}
                is UiState.Success -> {
                    items(trips.data) { trip ->
                        TripCard(
                            title = trip.name,
                            imageUrl = trip.imageUrl,
                            location = trip.location,
                            duration = trip.duration,
                            date = trip.startDate.formatFullDate(),
                            onViewTap = { onTripDetailsTap(trip.id) },
                            modifier = Modifier.padding(horizontal = 16f.dp, vertical = 6f.dp)
                        )
                    }
                }
            }
            item { Spacer(Modifier.height(20f.dp)) }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                dragHandle = {},
                sheetState = sheetState,
            ) {
                CreateTripSheet(
                    modifier = Modifier,
                    name = newTripName,
                    onNameChange = onNameChange,
                    travelStyle = travelStyle,
                    onTravelStyleChange = onTravelStyleChange,
                    description = description,
                    onDescriptionChange = onDescriptionChange,
                    isLoading = createTripState is CreateTripState.Submitting,
                    onDismiss = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    },
                    onSubmit = onSubmit
                )
            }
        }

        LaunchedEffect(createTripState) {
            val dismissSheet = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }
            when (createTripState) {
                is CreateTripState.Error -> {
                    dismissSheet()
                    scope.launch { snackbarHostState.showSnackbar(createTripState.message) }
                    onCreateTripError()
                }

                is CreateTripState.Submitted -> {
                    dismissSheet()
                    scope.launch { snackbarHostState.showSnackbar("Trip created") }
                    onCreateTripSuccess(createTripState.id)
                }

                else -> Unit
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        PlanTripScreen(
            destination = null,
            dateRange = Pair(null, null),
            onSelectCityTap = {},
            onSelectDateTap = {},
            trips = UiState.Success(
                List(20) {
                    Trip(
                        id = "",
                        name = "Amie Ramos",
                        imageUrl = "https://s3-alpha-sig.figma.com/img/d972/f5dd/6ae3bc4b07f5f3c7eee56d339f9c3f75?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=BqKpAXCqC9hjWrsGBqrO-DXbvvwer2dc5itjcIq71JMEfuqO4iMsyihoy4pkJkKg1w4qC~N58ZUpwmVEuWBu7GDtsx2M1~df6tdFKTCtiSHoNFeFpRKaGNmR8XNcjzsuaasiiHQ6SMn7V4ysi39wjMPHrMFHAjLWutlR7O0LLbtlnTp2PQslU-P~2SzFCcBJotf-Qk2B0xNYFy9PXQPML7HmDp1xyHbZzqXg7cJ7v728Y89W5Ux2qc1pudnLhUTDmaZy-6U4IySYtzNhGJID8xURPYzcjpSysr8SP0qMlwel6kcX1Z0jmVnwCY3uSwPJtdz90-qQ~7qHnLwepDwJgw__",
                        style = "Solo",
                        description = "Build, personalize, and optimize your itineraries with our trip planner. Perfect for getaways, remote workcations, and any spontaneous escapade.",
                        location = "Paris",
                        startDate = LocalDate.now().plusDays(4),
                        endDate = LocalDate.now().plusDays(14)
                    )
                }
            ),
            onTripDetailsTap = {},

            newTripName = "",
            onNameChange = {},
            travelStyle = "",
            onTravelStyleChange = { },
            description = "",
            onDescriptionChange = {},
            onSubmit = {},
            createTripState = CreateTripState.Idle,
            onCreateTripError = {},
            onCreateTripSuccess = {},
            onBackTap = {}
        )
    }
}