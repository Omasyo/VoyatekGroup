package com.example.voyatekgroup.ui.utils

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter

fun YearMonth.format(): String {
    return format(DateTimeFormatter.ofPattern("MMMM u"))// "${this.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${year}"
}

fun LocalDate.formatFullDateNoSuffix(): String {
    return format(DateTimeFormatter.ofPattern("d MMMM u"))
}

fun LocalDate.formatFullDate(): String {
    val suffix = when (dayOfMonth) {
        1 -> "st"
        2 -> "nd"
        else -> "th"
    }
    return format(DateTimeFormatter.ofPattern("d'$suffix' MMMM u"))
}

fun LocalDate.formatDateShort(): String {
    return format(DateTimeFormatter.ofPattern("dd-MM-u"))
}

fun LocalDate.formatDayMonth(): String {
    return format(DateTimeFormatter.ofPattern("ccc, MMM d"))
}

fun LocalDateTime.formatDate(): String {
    return toLocalDate().formatDayMonth()
}

fun LocalDateTime.formatTime(): String {
    return format(DateTimeFormatter.ofPattern("HH:mm"))
}

val DayOfWeek.short
    get() = when (this) {
        DayOfWeek.MONDAY -> "Mo"
        DayOfWeek.TUESDAY -> "Tu"
        DayOfWeek.WEDNESDAY -> "We"
        DayOfWeek.THURSDAY -> "Th"
        DayOfWeek.FRIDAY -> "Fr"
        DayOfWeek.SATURDAY -> "Sa"
        DayOfWeek.SUNDAY -> "Su"
    }