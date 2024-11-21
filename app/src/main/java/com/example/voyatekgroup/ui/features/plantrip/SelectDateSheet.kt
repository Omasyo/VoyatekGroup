package com.example.voyatekgroup.ui.features.plantrip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.R
import com.example.voyatekgroup.ui.components.FilledButton
import com.example.voyatekgroup.ui.components.SelectedDate
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.formatDayMonth
import java.time.LocalDate

@Composable
fun SelectDateSheet(
    modifier: Modifier = Modifier,
    startDate: LocalDate?,
    endDate: LocalDate?,
    selectedDate: SelectedDate,
    onStartDateTap: () -> Unit,
    onEndDateTap: () -> Unit,
    onChooseDateTap: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .drawBehind {
                drawLine(
                    Color(0xFFF0F2F5),
                    Offset(0f, 0f),
                    Offset(size.width, 0f),
                    1f.dp.toPx()
                )
            }
            .padding(horizontal = 16f.dp, vertical = 12f.dp)
    ) {
        Row {
            DateField(
                modifier = Modifier.weight(1f),
                title = "Start Date",
                date = startDate?.formatDayMonth(),
                selected = selectedDate.isStart,
                onDateTap = onStartDateTap
            )
            Spacer(Modifier.width(8f.dp))
            DateField(
                modifier = Modifier.weight(1f),
                title = "End Date",
                date = endDate?.formatDayMonth(),
                selected = selectedDate.isEnd,
                onDateTap = onEndDateTap
            )
        }
        Spacer(Modifier.height(18f.dp))
        FilledButton(
            text = "Choose Date",
            onClick = onChooseDateTap,
            enabled = startDate != null && endDate != null,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun DateField(
    modifier: Modifier = Modifier,
    title: String,
    date: String?,
    selected: Boolean,
    onDateTap: () -> Unit
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 4f.dp)
        )
        Spacer(Modifier.height(4f.dp))
        Row(
            modifier = Modifier
                .clickable(onClick = onDateTap)
                .border(
                    1f.dp,
                    if (selected) MaterialTheme.colorScheme.primary else Color(0xFF98A2B3),
                    RoundedCornerShape(4f.dp)
                )
                .padding(14f.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date ?: "Select Date",
                color = if (date == null) Color.Unspecified.copy(0.3f) else Color.Unspecified,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.weight(1f)
            )
            Icon(painterResource(R.drawable.calendar), null)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        Surface {
            SelectDateSheet(
                startDate = LocalDate.now(),
                endDate = LocalDate.now(),
                selectedDate = SelectedDate.End,
                onStartDateTap = {},
                onEndDateTap = {},
                onChooseDateTap = {},
            )
        }
    }
}