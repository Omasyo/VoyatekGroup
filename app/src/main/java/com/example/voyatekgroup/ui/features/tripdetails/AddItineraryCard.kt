package com.example.voyatekgroup.ui.features.tripdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.ui.components.FilledButton
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme

@Composable
fun AddItineraryCard(
    title: String,
    description: String,
    backgroundColor: Color,
    buttonColor: Color,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(backgroundColor),
    buttonContentColor: Color = MaterialTheme.colorScheme.contentColorFor(buttonColor),
    onAddItemTap: () -> Unit
) {
    Column(
        modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(backgroundColor)
            .padding(16f.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = contentColor
        )
        Spacer(Modifier.height(8f.dp))
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight =  FontWeight.W400),
            color = contentColor
        )
        Spacer(Modifier.height(36f.dp))
        FilledButton(
            text = "Add $title",
            onClick = onAddItemTap,
            color = buttonColor,
            contentColor = buttonContentColor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        Surface {
            AddItineraryCard(
                modifier = Modifier.padding(24f.dp),
                title = "Activities",
                description = "Build, personalize, and optimize your itineraries with our trip planner.",
                backgroundColor = MaterialTheme.colorScheme.secondary,
                buttonColor = MaterialTheme.colorScheme.primary,
                onAddItemTap = {}
            )
        }
    }
}