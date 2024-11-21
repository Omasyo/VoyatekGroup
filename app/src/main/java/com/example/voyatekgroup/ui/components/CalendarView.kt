package com.example.voyatekgroup.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import java.time.LocalDate
import java.time.YearMonth

enum class SelectedDate {
    Start, End;

    val isStart get() = this == Start
    val isEnd get() = !isStart
}

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    startMonth: YearMonth,
    onTapDate: (LocalDate) -> Unit,
    onTapSelectedDate: (SelectedDate) -> Unit,
    selectedDates: Pair<LocalDate?, LocalDate?>
//    onTapDate: (LocalDate)
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24f.dp)
    ) {
        items(48) {
            MonthView(
                modifier = Modifier.padding(
                    horizontal = 16f.dp,
                    vertical = 8f.dp
                ),
                month = startMonth.plusMonths(it.toLong()),
                onTapDate = onTapDate,
                onTapSelectedDate = {
                    val selectedDate =
                        if (it == selectedDates.first) SelectedDate.Start else SelectedDate.End
                    onTapSelectedDate(selectedDate)
                },
                selectedDates = selectedDates
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {

    var selectedDate by remember { mutableStateOf(SelectedDate.Start) }
    var date by remember {
        mutableStateOf(
            Pair<LocalDate?, LocalDate?>(
                LocalDate.now(),
                null
            )
        )
    }
    VoyatekGroupTheme {
        Surface {
            CalendarView(
                startMonth = YearMonth.now(),
                onTapDate = {
                    if (selectedDate.isStart &&
                        date.second != null &&
                        it > date.second
                    ) {
                        return@CalendarView
                    }
                    if (selectedDate.isEnd &&
                        it < date.first
                    ) {
                        return@CalendarView
                    }
                    date =
                        if (selectedDate.isStart) {
                            selectedDate = SelectedDate.End
                            date.copy(first = it)
                        } else date.copy(second = it)
                },
                selectedDates = date,
                onTapSelectedDate = {
                    selectedDate = it
                }
            )
        }
    }
}