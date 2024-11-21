package com.example.voyatekgroup.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.voyatekgroup.R
import com.example.voyatekgroup.models.HotelItinerary
import com.example.voyatekgroup.models.RiveraResort
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.formatDateShort

@Composable
fun HotelItineraryPlaceholder(
    modifier: Modifier,
    onAddHotel: () -> Unit
) {
    ItineraryPlaceholder(
        modifier = modifier,
        title = "Hotels",
        iconId = R.drawable.buildings,
        backgroundColor = MaterialTheme.colorScheme.tertiary,
        illustrationId = R.drawable.hotel,
        actionText = "Add Hotel",
        onAction = onAddHotel
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotelItineraryCard(
    modifier: Modifier = Modifier,
    hotelItinerary: HotelItinerary,
    onRemoveTap: () -> Unit,
) {
    with(hotelItinerary) {
        ItineraryCard(
            modifier = modifier,
            title = "Hotels",
            iconId = R.drawable.hotel,
            backgroundColor = MaterialTheme.colorScheme.tertiary,
        ) {
            Column {
                val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
                Box(
                    modifier = Modifier
                        .padding(16f.dp)
                        .fillMaxWidth()
                        .height(224f.dp)
                        .clip(MaterialTheme.shapes.extraLarge)
                ) {

                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true).build(),
                        contentDescription = null,
                        placeholder = remember(surfaceVariant) { ColorPainter(surfaceVariant) },
                        error = remember(surfaceVariant) { ColorPainter(surfaceVariant) },
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(20f.dp)
                            .clip(MaterialTheme.shapes.medium)
                            .background(Color.Black.copy(0.3f))
                            .padding(horizontal = 12f.dp, vertical = 8f.dp)
                    ) {
                        Icon(
                            painterResource(R.drawable.caret_down),
                            null,
                            modifier = Modifier
                                .size(26f.dp)
                                .rotate(90f)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(4f.dp)
                        )
                        Spacer(Modifier.width(48f.dp))
                        Icon(
                            painterResource(R.drawable.caret_down),
                            null,
                            modifier = Modifier
                                .size(26f.dp)
                                .rotate(-90f)
                                .clip(CircleShape)
                                .background(Color.White)
                                .padding(4f.dp)
                        )
                    }
                }

                Spacer(Modifier.height(16f.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 16f.dp),
                    verticalArrangement = Arrangement.spacedBy(8f.dp)
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = address,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    FlowRow (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { }
                        ) {
                            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.primary) {
                                Icon(
                                    painterResource(R.drawable.map_outlined),
                                    contentDescription = null,
                                    modifier = Modifier.size(16f.dp)
                                )
                                Spacer(Modifier.width(2f.dp))
                                Text(
                                    text = "Show in map",
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { }
                        ) {
                            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = Color(0xFFF4B93E),
                                    modifier = Modifier.size(16f.dp)
                                )
                                Spacer(Modifier.width(2f.dp))
                                Text(
                                    text = "8.5 (436)",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { }
                        ) {
                            CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                                Icon(
                                    painterResource(R.drawable.bed),
                                    contentDescription = null,
                                    modifier = Modifier.size(16f.dp)
                                )
                                Spacer(Modifier.width(2f.dp))
                                Text(
                                    text = roomSize,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(10f.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16f.dp, vertical = 8f.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                        Row {
                            Icon(
                                painterResource(R.drawable.calendar),
                                null
                            )
                            Spacer(Modifier.width(4f.dp))
                            Text(
                                text = "In: ${inDate.formatDateShort()}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                        Row {
                            Icon(
                                painterResource(R.drawable.calendar),
                                null
                            )
                            Spacer(Modifier.width(4f.dp))
                            Text(
                                text = "Out: ${outDate.formatDateShort()}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16f.dp, vertical = 8f.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    listOf("Hotel details", "Price details", "Edit details").forEach { text ->
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


@Preview(device = "spec:width=900px,height=2340px,dpi=440")
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        Surface {
            HotelItineraryCard(
                modifier = Modifier
                    .padding(24f.dp),
                hotelItinerary = RiveraResort,
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
            HotelItineraryPlaceholder(
                modifier = Modifier
                    .padding(24f.dp)
            ) {

            }
        }
    }
}