package com.example.voyatekgroup.ui.components

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.R
import com.example.voyatekgroup.models.AmericanAirlines
import com.example.voyatekgroup.models.FlightItinerary
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.formatDate
import com.example.voyatekgroup.ui.utils.formatTime
import java.time.LocalDateTime

@Composable
fun FlightItineraryPlaceholder(
    modifier: Modifier,
    onAddFlight: () -> Unit
) {
    ItineraryPlaceholder(
        modifier = modifier,
        title = "Flights",
        iconId = R.drawable.airplane_in_flight,
        backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        illustrationId = R.drawable.plane_wing,
        actionText = "Add Flight",
        onAction = onAddFlight
    )
}

@Composable
fun FlightItineraryCard(
    modifier: Modifier = Modifier,
    flightItinerary: FlightItinerary,
    onRemoveTap: () -> Unit,
) {
    with(flightItinerary) {
        ItineraryCard(
            modifier = modifier,
            title = "Flights",
            iconId = R.drawable.airplane_in_flight,
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 16f.dp)
                        .padding(top = 20f.dp)
                ) {
                    Image(painterResource(R.drawable.american_airlines), null)
                    Spacer(Modifier.width(12f.dp))
                    Column {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.height(4f.dp))
                        Text(
                            text = code,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Spacer(Modifier.height(12f.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .widthIn(max = 360f.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16f.dp)
                        .padding(bottom = 10f.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        24f.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    Time(fromTime)
                    Column(
                        modifier = Modifier
                            .widthIn(80f.dp, 120f.dp)
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12f.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                painterResource(R.drawable.airplane_takeoff),
                                contentDescription = null,
                                tint = Color(0xFF475367)
                            )
                            Text(
                                text = duration,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Icon(
                                painterResource(R.drawable.airplane_landing),
                                contentDescription = null,
                                tint = Color(0xFF475367)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8f.dp))
                                .fillMaxWidth()
                                .height(8f.dp)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 30f.dp)
                                .clip(RoundedCornerShape(8f.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = fromLocation,
                                style = MaterialTheme.typography.titleSmall
                            )
                            Text(
                                text = type,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = toLocation,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    }
                    Time(toTime)
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16f.dp, vertical = 8f.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Flight details", "Price details", "Edit details").forEach { text ->
                        Text(
                            text = text,
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                Text(
                    text = price,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16f.dp)
                )

                Row(
                    modifier = Modifier
                        .clickable(onClick = onRemoveTap)
                        .fillMaxWidth()
                        .height(70f.dp)
                        .background(MaterialTheme.colorScheme.errorContainer),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.error) {
                        Text(
                            text = "Remove",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.width(8f.dp))
                        Icon(painterResource(R.drawable.close), null)
                    }
                }
            }
        }
    }
}

@Composable
private fun Time(
    time: LocalDateTime,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = time.formatTime(),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = time.formatDate(),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(fontScale = 1.0f)
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        Surface {
            FlightItineraryCard(
                modifier = Modifier
                    .padding(24f.dp),
                flightItinerary = AmericanAirlines,
                onRemoveTap = {}
            )
        }
    }
}

@Preview
@Composable
private fun PlaceholderPreview() {
    VoyatekGroupTheme {
        Surface {
            FlightItineraryPlaceholder(
                modifier = Modifier
                    .padding(24f.dp)
            ) {

            }
        }
    }
}