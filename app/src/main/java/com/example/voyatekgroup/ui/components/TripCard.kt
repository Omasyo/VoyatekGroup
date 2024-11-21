package com.example.voyatekgroup.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme

@Composable
fun TripCard(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    location: String,
    duration: String,
    date: String,
    onViewTap: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .border(
                width = 1f.dp,
                color = Color(0xFFE4E7EC),
                shape = MaterialTheme.shapes.extraLarge
            )
            .clip(MaterialTheme.shapes.extraLarge)
            .padding(
                horizontal = 16f.dp,
                vertical = 12f.dp
            ),
        verticalArrangement = Arrangement.spacedBy(14f.dp)
    ) {
        Box(
            Modifier
                .height(230f.dp)
                .clip(MaterialTheme.shapes.extraLarge)
        ) {
            val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
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
            Text(
                text = location,
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(18f.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(Color.Black.copy(0.2f))
                    .padding(horizontal = 32f.dp, vertical = 12f.dp)
//                    .blur()
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium
        )
        Row {
            Text(
                text = date,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = duration,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        FilledButton(
            text = "View",
            onClick = onViewTap,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme() {
        Box(
            Modifier
                .background(Color.White)
                .padding(20f.dp)
        ) {
            TripCard(
                title = "Bahamas Family Trip",
                imageUrl = "",
                location = "Paris",
                duration = "5 days",
                date = "19th April 2024",
                onViewTap = {}
            )
        }
    }
}