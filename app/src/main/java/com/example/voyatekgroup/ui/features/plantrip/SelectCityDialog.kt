package com.example.voyatekgroup.ui.features.plantrip

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.voyatekgroup.R
import com.example.voyatekgroup.models.City
import com.example.voyatekgroup.ui.components.TextField
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme

@Composable
fun SelectCityRoute(
    modifier: Modifier = Modifier,
    onCloseTap: () -> Unit,
    viewModel: PlanTripViewModel
) {
    SelectCityDialog(
        modifier = modifier,
        searchQuery = viewModel.searchQuery.collectAsStateWithLifecycle().value,
        onSearchQueryChange = viewModel::changeSearchQuery,
        cities = viewModel.cities.collectAsStateWithLifecycle().value,
        onCityTap = {
            viewModel.selectCity(it)
            onCloseTap()
        },
        onCloseTap = onCloseTap
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SelectCityDialog(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    cities: List<City>,
    onCityTap: (City) -> Unit,
    onCloseTap: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Where") },
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
//            contentPadding = innerPadding
        ) {
            stickyHeader {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(top = 20f.dp)
                        .padding(horizontal = 16f.dp)
                        .padding(bottom = 10f.dp)
                ) {
                    Text(
                        text = "Please select a city",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(Modifier.height(16f.dp))
                    TextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        textStyle = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                2f.dp,
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.shapes.large
                            )
                            .padding(horizontal = 20f.dp, vertical = 26f.dp)
                    )
                }
            }
            items(cities) { city ->
                Row(
                    modifier = Modifier
                        .clickable { onCityTap(city) }
                        .padding(horizontal = 16f.dp)
                        .padding(horizontal = 16f.dp, vertical = 20f.dp),
                    horizontalArrangement = Arrangement.spacedBy(18f.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource(R.drawable.map_filled),
                        contentDescription = null,
                        tint = Color(0xFF667185)
                    )
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = city.city,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W900)
                        )
                        Text(
                            text = city.location,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.nigeria),
                            contentDescription = null,
                        )
                        Spacer(Modifier.height(2f.dp))
                        Text(
                            text = city.short,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    VoyatekGroupTheme {
        SelectCityDialog(
            searchQuery = "Sea",
            onSearchQueryChange = {},
            cities = List(20) {
                City(
                    city = "Laghouat Algeria ",
                    location = "Muritala Muhammed",
                    "NG"
                )
            },
            onCityTap = {},
            onCloseTap = {}
        )
    }
}