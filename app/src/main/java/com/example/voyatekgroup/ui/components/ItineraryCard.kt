package com.example.voyatekgroup.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
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
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme

@Composable
fun ItineraryCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes iconId: Int,
    backgroundColor: Color,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(backgroundColor),
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(backgroundColor)
            .padding(16f.dp)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            Row {
                Icon(painterResource(iconId), null)
                Spacer(Modifier.width(10f.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        Spacer(Modifier.height(16f.dp))
        Surface(
            color = MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.extraLarge
        ) {
            content()
        }
    }
}


@Composable
fun ItineraryPlaceholder(
    modifier: Modifier,
    title: String,
    @DrawableRes iconId: Int,
    backgroundColor: Color,
    @DrawableRes illustrationId: Int,
    actionText: String,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(backgroundColor),
    onAction: () -> Unit
) {
    ItineraryCard(
        modifier = modifier,
        title = title,
        iconId = iconId,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32f.dp, vertical = 54f.dp)
                .padding(horizontal = 16f.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(illustrationId),
                contentDescription = null
            )
            Spacer(Modifier.height(8f.dp))
            Text(
                text = "No request yet",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(12f.dp))
            FilledButton(
                text = actionText,
                onClick = onAction,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        Surface {
            ItineraryCard(
                modifier = Modifier
                    .padding(24f.dp),
                title = "Flights",
                iconId = R.drawable.airplane_in_flight,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(400f.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PlaceholderPreview() {
    VoyatekGroupTheme {
        Surface {
            ItineraryPlaceholder(
                modifier = Modifier
                    .padding(24f.dp),
                title = "Flights",
                iconId = R.drawable.airplane_in_flight,
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
                illustrationId = R.drawable.plane_wing,
                actionText = "Action Text",
                onAction = {}
            )
        }
    }
}