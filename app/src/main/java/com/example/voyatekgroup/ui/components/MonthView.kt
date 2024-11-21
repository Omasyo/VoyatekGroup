package com.example.voyatekgroup.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import com.example.voyatekgroup.ui.utils.format
import com.example.voyatekgroup.ui.utils.short
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjusters

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MonthView(
    modifier: Modifier = Modifier,
    month: YearMonth,
    onTapDate: (LocalDate) -> Unit,
    onTapSelectedDate: (LocalDate) -> Unit,
    selectedDates: Pair<LocalDate?, LocalDate?>,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            month.format(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16f.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8f.dp, Alignment.CenterHorizontally)
        ) {
            for (day in DayOfWeek.entries) {
                Text(
                    text = day.short,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(4f.dp)
                )
            }
        }
        Spacer(Modifier.height(8f.dp))
        FlowRow(
            maxItemsInEachRow = 7,
            horizontalArrangement = Arrangement.spacedBy(8f.dp),
            verticalArrangement = Arrangement.spacedBy(8f.dp)
        ) {

            val startDate = remember(month) {
                month.atDay(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            }
            val range = remember(startDate) {
                val endDate =
                    month.atEndOfMonth().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                ChronoUnit.DAYS.between(startDate, endDate)
            }
            val days = (0..range).map {
                startDate.plusDays(it)
            }

            for (date in days) {
                val disabled = month.monthValue != date.monthValue || date < LocalDate.now()
                val selected = (selectedDates.first == date) || (selectedDates.second == date)
                Surface(
                    Modifier
                        .weight(1f)
                        .clip(MaterialTheme.shapes.extraSmall)
                        .clickable(enabled = !disabled) {
                            if (selected) {
                                onTapSelectedDate(date)
                            } else {
                                onTapDate(date)
                            }
                        },
                    color = if (selected && !disabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        textAlign = TextAlign.Center,
                        color = if (disabled) Color.Unspecified.copy(
                            0.25f
                        ) else Color.Unspecified,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(8f.dp)
                    )
                }
            }
        }
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
private fun MonthViewPreview() {
    var first by remember { mutableStateOf(true) }
    var date by remember {
        mutableStateOf(
            Pair<LocalDate?, LocalDate?>(
                LocalDate.now(),
                LocalDate.now().plusDays(9)
            )
        )
    }
    VoyatekGroupTheme {
        Surface {
            MonthView(
                month = YearMonth.now(),
                onTapDate = {
                    if (first && it > date.second) return@MonthView
                    if(!first && it < date.first) return@MonthView
                    date = if (first) date.copy(first = it) else date.copy(second = it)
                },
                selectedDates = date,
                onTapSelectedDate = {
                    first = it == date.first
                }
            )
        }
    }
}