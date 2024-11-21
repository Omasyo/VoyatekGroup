package com.example.voyatekgroup.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0D6EFD),
    primaryContainer = Color(0xFFEDF7F9),
    secondary = Color(0xFF000031),
    secondaryContainer = Color(0xFFE7F0FF),
    tertiary = Color(0xFF344054),
    surface = Color.White,
    onSurface = Color(0xFF1D2433),
    background = Color.White,
    onBackground = Color(0xFF1D2433),
    surfaceVariant = Color(0xFFF0F2F5),
    onSurfaceVariant = Color(0xFF676E7E),
    error = Color(0xFF9E0A05),
    errorContainer = Color(0xFFFBEAE9)
//    newBorderColor = Color(0xFF98A2B3)
//    disabledColor = Color(0xFFC4C4C4)
//    smallBoxColors = Color(0xFFF7F9FC)
//    darkerIconColor = Color(0xFF667185)
//    lighterFont = Color(0xFF647995)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun VoyatekGroupTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = MaterialTheme.shapes.copy(
            extraSmall = RoundedCornerShape(4f.dp),
            small = RoundedCornerShape(4f.dp),
            medium = RoundedCornerShape(4f.dp),
            large = RoundedCornerShape(4f.dp),
            extraLarge = RoundedCornerShape(4f.dp)
        ),
        content = content
    )
}