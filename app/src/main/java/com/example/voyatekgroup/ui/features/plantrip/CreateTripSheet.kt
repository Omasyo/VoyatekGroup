package com.example.voyatekgroup.ui.features.plantrip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.voyatekgroup.R
import com.example.voyatekgroup.ui.components.FilledButton
import com.example.voyatekgroup.ui.components.TextField
import com.example.voyatekgroup.ui.theme.VoyatekGroupTheme

@Composable
fun CreateTripSheet(
    modifier: Modifier = Modifier,
    name: String,
    onNameChange: (String) -> Unit,
    travelStyle: String?,
    onTravelStyleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    isLoading: Boolean,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(20f.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painterResource(R.drawable.tree_palm),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(14f.dp)
            )
            IconButton(onClick = onDismiss) {
                Icon(painterResource(R.drawable.close), null)
            }
        }
        Spacer(Modifier.height(16f.dp))
        Text(
            text = "Create a Trip",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(Modifier.height(4f.dp))
        Text(
            text = "Let's Go! Build Your Next Adventure",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(20f.dp))
        SheetTextField(
            title = "Trip Name",
            value = name,
            onValueChange = onNameChange,
            placeholder = "Enter the trip name",
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            )
        )

        Spacer(Modifier.height(12f.dp))
        DropDownField(
            title = "Travel Style",
            value = travelStyle,
            onValueChange = onTravelStyleChange,
            placeholder = "Select your travel style",
            options = listOf("Solo", "Couple", "Family", "Group")
        )
        Spacer(Modifier.height(12f.dp))
        SheetTextField(
            title = "Trip Description",
            value = description,
            onValueChange = onDescriptionChange,
            placeholder = "Tell us more about the trip",
            minLines = 5,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            )
        )
        Spacer(Modifier.height(12f.dp))
        FilledButton(
            text = "Next",
            onClick = onSubmit,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        )
    }
}

@Composable
private fun DropDownField(
    title: String,
    value: String?,
    onValueChange: (String) -> Unit,
    placeholder: String,
    options: List<String>,
    modifier: Modifier = Modifier,
) {

    var expanded by remember { mutableStateOf(false) }

    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 4f.dp)
        )
        Spacer(Modifier.height(4f.dp))
        Box(
            Modifier
                .clickable { expanded = true }
                .fillMaxWidth()
                .border(
                    1f.dp,
                    Color(0xFF98A2B3),
                    MaterialTheme.shapes.large
                )
                .padding(horizontal = 14f.dp, vertical = 14f.dp)
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = value ?: placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (value == null) Color.Unspecified.copy(0.35f) else Color.Unspecified
                )
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Unspecified.copy(0.35f)
                )

            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .padding(horizontal = 16f.dp)
                .fillMaxWidth(),
            properties = PopupProperties(
                focusable = true,
            )
        ) {
            for (index in options.indices) {

                DropdownMenuItem(
                    text = {
                        Text(
                            options[index],
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    onClick = {
                        onValueChange(options[index])
                        expanded = false
                    })
            }
        }
    }
}

@Composable
private fun SheetTextField(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    Column(modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 4f.dp)
        )
        Spacer(Modifier.height(4f.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder,
            minLines = minLines,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    1f.dp,
                    Color(0xFF98A2B3),
                    MaterialTheme.shapes.large
                )
                .padding(horizontal = 14f.dp, vertical = 14f.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    var travelStyle by remember { mutableStateOf<String?>(null) }
    VoyatekGroupTheme {
        CreateTripSheet(
            name = "",
            onNameChange = {},
            travelStyle = travelStyle,
            onTravelStyleChange = { travelStyle = it },
            description = "",
            onDescriptionChange = {},
            onDismiss = {},
            onSubmit = {},
            isLoading = true
        )
    }
}