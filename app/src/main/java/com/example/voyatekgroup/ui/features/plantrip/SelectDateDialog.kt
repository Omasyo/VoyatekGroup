package com.example.voyatekgroup.ui.features.plantrip

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.voyatekgroup.R
import com.example.voyatekgroup.ui.components.CalendarView
import com.example.voyatekgroup.ui.components.SelectedDate
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun SelectDateRoute(
    modifier: Modifier = Modifier,
    onCloseTap: () -> Unit,
    viewModel: PlanTripViewModel
) {

    SelectDateDialog(
        modifier = modifier,
        startMonth = remember { YearMonth.now() },
        onTapDate = viewModel::setDate,
        selectedDate = viewModel.selectedDate,
        onTapSelectedDate = viewModel::changeSelectedDate,
        selectedDates = viewModel.dateRange,
        onCloseTap =  onCloseTap
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateDialog(
    modifier: Modifier = Modifier,
    startMonth: YearMonth,
    onTapDate: (LocalDate) -> Unit,
    selectedDate: SelectedDate,
    onTapSelectedDate: (SelectedDate) -> Unit,
    selectedDates: Pair<LocalDate?, LocalDate?>,
    onCloseTap: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Date") },
                navigationIcon = {
                    IconButton(onClick = onCloseTap) {
                        Icon(painterResource(R.drawable.close), null)
                    }
                },
                modifier = Modifier.drawBehind {
                    drawLine(
                        Color(0xFFE4E7EC),
                        Offset(0f, size.height),
                        Offset(size.width, size.height),
                        1f.dp.toPx()
                    )
                }
            )
        },
        bottomBar = {
            SelectDateSheet(
                startDate = selectedDates.first,
                endDate = selectedDates.second,
                selectedDate = selectedDate,
                onStartDateTap = { onTapSelectedDate(SelectedDate.Start) },
                onEndDateTap = { onTapSelectedDate(SelectedDate.End) },
                onChooseDateTap = onCloseTap
            )
        }
    ) { contentPadding ->
        CalendarView(
            modifier = Modifier.padding(contentPadding),
            startMonth = startMonth,
            onTapDate = onTapDate,
            onTapSelectedDate = onTapSelectedDate,
            selectedDates = selectedDates
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        Surface {
            SelectDateDialog(
                startMonth = YearMonth.now(),
                onTapDate = {},
                selectedDate = SelectedDate.Start,
                onTapSelectedDate = {},
                selectedDates = Pair(LocalDate.now(), null),
                onCloseTap = {}
            )
        }
    }
}