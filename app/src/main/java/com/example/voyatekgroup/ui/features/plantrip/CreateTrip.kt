package com.example.voyatekgroup.ui.features.plantrip

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.R
import com.example.voyatekgroup.ui.components.FilledButton
import com.example.voyatekgroup.ui.components.SelectedDate
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.formatDayMonth
import java.time.LocalDate

@Composable
fun CreateTrip(
    modifier: Modifier = Modifier,
    destination: String?,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onSelectCityTap: () -> Unit,
    onSelectDateTap: (SelectedDate) -> Unit,
    onCreateTripTap: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16f.dp),
        verticalArrangement = Arrangement.spacedBy(8f.dp)
    ) {
        Section(
            title = "Where to ?",
            content = destination,
            placeholder = "Select City",
            iconId = R.drawable.map_outlined,
            onTap = onSelectCityTap,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Section(
                title = "Start Date",
                content = startDate?.formatDayMonth(),
                placeholder = "Enter Date",
                iconId = R.drawable.calendar,
                onTap = { onSelectDateTap(SelectedDate.Start) },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(8f.dp))
            Section(
                title = "End Date",
                content = endDate?.formatDayMonth(),
                placeholder = "Enter Date",
                iconId = R.drawable.calendar,
                onTap = {
                    onSelectDateTap(if (startDate == null) SelectedDate.Start else SelectedDate.End)
                },
                modifier = Modifier.weight(1f)
            )
        }
        FilledButton(
            text = "Create a Trip",
            onClick = onCreateTripTap,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W900)
        )
    }
}

@Composable
private fun Section(
    modifier: Modifier = Modifier,
    title: String,
    content: String?,
    placeholder: String,
    @DrawableRes iconId: Int,
    onTap: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .clickable(onClick = onTap)
            .border(1f.dp, Color(0xFFE4E7EC))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 10f.dp, vertical = 22f.dp),
        horizontalArrangement = Arrangement.spacedBy(8f.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(iconId),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = content ?: placeholder,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.W900),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        CreateTrip(
            destination = "Enugu",
            startDate = LocalDate.now(),
            endDate = null,
            onSelectCityTap = {},
            onSelectDateTap = {},
            onCreateTripTap = {}
        )
    }
}