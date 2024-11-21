package com.example.voyatekgroup.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun FilledButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    style: TextStyle? = null
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 24f.dp,
            vertical = 16.dp
        ),
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = contentColor
        ),
        modifier = modifier
    ) {
        Text(
            text,
            style = style ?: MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun OutlinedButton(
    text: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: TextStyle? = null
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        contentPadding = PaddingValues(
            horizontal = 16f.dp,
            vertical = 16.dp
        ),
        enabled = enabled,
        shape = MaterialTheme.shapes.large,
        border = BorderStroke(1f.dp, MaterialTheme.colorScheme.primary),
        modifier = modifier
    ) {
        Row {
            Icon(
                painterResource(iconId),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.width(4f.dp))
            Text(
                text,
                style = style ?: MaterialTheme.typography.bodyMedium
            )
        }
    }
}