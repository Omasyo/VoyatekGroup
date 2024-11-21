package com.example.voyatekgroup.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.voyatekgroup.R

val satoshiFamily = FontFamily(
    Font(R.font.satoshi_light, FontWeight.Light),
    Font(R.font.satoshi_regular, FontWeight.Normal),
    Font(R.font.satoshi_medium, FontWeight.Medium),
    Font(R.font.satoshi_bold, FontWeight.Bold),
    Font(R.font.satoshi_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.5).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.5).sp
    ),
    bodySmall = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),
    titleLarge =  TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.5).sp
    ),
    titleMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.W700,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.5).sp
    ),
    titleSmall = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.5).sp
    ),
)