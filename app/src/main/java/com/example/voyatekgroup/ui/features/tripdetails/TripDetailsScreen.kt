package com.example.voyatekgroup.ui.features.tripdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.voyatekgroup.R
import com.example.voyatekgroup.models.ActivityItinerary
import com.example.voyatekgroup.models.AmericanAirlines
import com.example.voyatekgroup.models.FlightItinerary
import com.example.voyatekgroup.models.HotelItinerary
import com.example.voyatekgroup.models.Itinerary
import com.example.voyatekgroup.models.Museum
import com.example.voyatekgroup.models.RiveraResort
import com.example.voyatekgroup.models.Trip
import com.example.voyatekgroup.ui.components.ActivityItineraryCard
import com.example.voyatekgroup.ui.components.ActivityItineraryPlaceholder
import com.example.voyatekgroup.ui.components.FilledButton
import com.example.voyatekgroup.ui.components.FlightItineraryCard
import com.example.voyatekgroup.ui.components.FlightItineraryPlaceholder
import com.example.voyatekgroup.ui.components.HotelItineraryCard
import com.example.voyatekgroup.ui.components.HotelItineraryPlaceholder
import com.example.voyatekgroup.ui.components.OutlinedButton
import com.example.voyatekgroup.ui.components.UiState
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.formatFullDateNoSuffix

@Composable
fun TripDetailsRoute(
    modifier: Modifier = Modifier,
    onBackTap: () -> Unit,
    viewModel: TripDetailsViewModel
) {
    TripDetailsScreen(
        modifier = modifier,
        trip = viewModel.trip.collectAsStateWithLifecycle().value,
        onAddItinerary = viewModel::addItinerary,
        onRemoveItinerary = viewModel::removeItinerary,
        itineraries = viewModel.itineraries,
        onRefresh = viewModel::refresh,
        onBackTap = onBackTap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailsScreen(
    modifier: Modifier = Modifier,
    trip: UiState<Trip>,
    onAddItinerary: (Itinerary) -> Unit,
    onRemoveItinerary: (Itinerary) -> Unit,
    itineraries: List<Itinerary>,
    onRefresh: () -> Unit,
    onBackTap: () -> Unit
) {

    Scaffold(
        modifier = modifier,
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
                    Image(
                        painterResource(
                            R.drawable.beach
                        ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 400f.dp)
                    )
                    when (trip) {
                        is UiState.Error -> {
                            ErrorPlaceholder(
                                modifier = Modifier.fillParentMaxSize(),
                                onRetry = onRefresh
                            )
                        }

                        UiState.Loading -> {
                            LoadingPlaceholder()
                        }

                        is UiState.Success -> {
                            Column(
                                modifier = Modifier.padding(horizontal = 16f.dp)
                            ) {
                                Spacer(Modifier.height(16f.dp))
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(2f.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painterResource(R.drawable.calendar),
                                        contentDescription = null,
                                        modifier = Modifier.size(12f.dp)
                                    )
                                    Text(
                                        text = trip.data.startDate.formatFullDateNoSuffix(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                    Icon(
                                        painterResource(R.drawable.arrow_left),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(12f.dp)
                                            .rotate(180f)
                                    )
                                    Text(
                                        text = trip.data.startDate.formatFullDateNoSuffix(),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                Spacer(Modifier.height(8f.dp))
                                Text(
                                    text = trip.data.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(Modifier.height(4f.dp))
                                Text(
                                    text = "${trip.data.location} | ${trip.data.style} Trip",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(Modifier.height(16f.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedButton(
                                        text = "View",
                                        iconId = R.drawable.handshake,
                                        onClick = {},
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(Modifier.width(12f.dp))
                                    OutlinedButton(
                                        text = "Share Trip",
                                        iconId = R.drawable.share,
                                        onClick = {},
                                        modifier = Modifier.weight(1f)
                                    )
                                    Spacer(Modifier.width(4f.dp))
                                    IconButton(onClick = {}) {
                                        Icon(painterResource(R.drawable.dots_three), null)
                                    }
                                }
                                Spacer(Modifier.height(16f.dp))
                                AddItineraryCard(
                                    modifier = Modifier.padding(
                                        vertical = 4f.dp
                                    ),
                                    title = "Activities",
                                    description = "Build, personalize, and optimize your itineraries with our trip planner.",
                                    backgroundColor = MaterialTheme.colorScheme.secondary,
                                    buttonColor = MaterialTheme.colorScheme.primary,
                                    onAddItemTap = { onAddItinerary(Museum) }
                                )

                                AddItineraryCard(
                                    modifier = Modifier.padding(
                                        vertical = 4f.dp
                                    ),
                                    title = "Hotels",
                                    description = "Build, personalize, and optimize your itineraries with our trip planner.",
                                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                                    buttonColor = MaterialTheme.colorScheme.primary,
                                    onAddItemTap = { onAddItinerary(RiveraResort) }
                                )

                                AddItineraryCard(
                                    modifier = Modifier.padding(
                                        vertical = 4f.dp
                                    ),
                                    title = "Flights",
                                    description = "Build, personalize, and optimize your itineraries with our trip planner.",
                                    backgroundColor = MaterialTheme.colorScheme.primary,
                                    buttonColor = MaterialTheme.colorScheme.surface,
                                    onAddItemTap = { onAddItinerary(AmericanAirlines) }
                                )

                                Spacer(Modifier.height(24f.dp))
                                Text(
                                    text = "Trip itineraries",
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Spacer(Modifier.height(4f.dp))
                                Text(
                                    text = "Your trip itineraries are placed here",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(Modifier.height(16f.dp))
                            }
                        }
                    }
                }
            }
            items(itineraries) { itinerary ->
                val itineraryModifier =
                    Modifier
                        .padding(horizontal = 16f.dp, vertical = 8f.dp)
                        .animateItem()
                when (itinerary) {
                    is ActivityItinerary -> {
                        ActivityItineraryCard(
                            activityItinerary = itinerary,
                            onRemoveTap = { onRemoveItinerary(itinerary) },
                            modifier = itineraryModifier
                        )
                    }

                    is FlightItinerary -> {
                        FlightItineraryCard(
                            flightItinerary = itinerary,
                            onRemoveTap = { onRemoveItinerary(itinerary) },
                            modifier = itineraryModifier
                        )
                    }

                    is HotelItinerary -> {
                        HotelItineraryCard(
                            hotelItinerary = itinerary,
                            onRemoveTap = { onRemoveItinerary(itinerary) },
                            modifier = itineraryModifier
                        )
                    }
                }
            }
            item {
                val hasFlight = remember(itineraries) { itineraries.any { it is FlightItinerary } }
                val hasActivity =
                    remember(itineraries) { itineraries.any { it is ActivityItinerary } }
                val hasHotel = remember(itineraries) { itineraries.any { it is HotelItinerary } }
                val placeholderModifier =
                    Modifier
                        .padding(horizontal = 16f.dp, vertical = 4f.dp)
                        .animateItem()
                Column {

                    if (!hasFlight) {
                        FlightItineraryPlaceholder(
                            modifier = placeholderModifier,
                            onAddFlight = { onAddItinerary(AmericanAirlines) }
                        )
                    }
                    if (!hasHotel) {
                        HotelItineraryPlaceholder(
                            modifier = placeholderModifier,
                            onAddHotel = { onAddItinerary(RiveraResort) }
                        )
                    }
                    if (!hasActivity) {
                        ActivityItineraryPlaceholder(
                            modifier = placeholderModifier,
                            onAddActivity = { onAddItinerary(Museum) }
                        )
                    }
                    Spacer(Modifier.height(24f.dp))
                }
            }
        }
    }
}

@Composable
private fun LoadingPlaceholder(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16f.dp)
    ) {
        Spacer(Modifier.height(16f.dp))
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(20f.dp)
                .width(160f.dp)
        )
        Spacer(Modifier.height(8f.dp))
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(24f.dp)
                .width(120f.dp)
        )
        Spacer(Modifier.height(4f.dp))
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(16f.dp)
                .width(160f.dp)
        )
        Spacer(Modifier.height(16f.dp))
        Row {
            Box(
                Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .height(60f.dp)
                    .weight(1f)
            )
            Spacer(Modifier.width(16f.dp))
            Box(
                Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .height(60f.dp)
                    .weight(1f)
            )
        }
        Spacer(Modifier.height(16f.dp))
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(120f.dp)
                .fillMaxWidth()
        )
        Spacer(Modifier.height(16f.dp))
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(120f.dp)
                .fillMaxWidth()
        )

        Spacer(Modifier.height(16f.dp))
        Box(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .height(120f.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun ErrorPlaceholder(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier.padding(top = 120f.dp),
        verticalArrangement = Arrangement.spacedBy(8f.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "An Error Occurred",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W900),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        FilledButton(
            text = "Retry",
            onClick = onRetry
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        TripDetailsScreen(
            trip = UiState.Error(""),// .Success(exampleTrip),
            onAddItinerary = {},
            onRemoveItinerary = {},
            itineraries = listOf(
                RiveraResort,
                Museum,
                AmericanAirlines
            ),
            onRefresh = {},
            onBackTap = {}
        )
    }
}