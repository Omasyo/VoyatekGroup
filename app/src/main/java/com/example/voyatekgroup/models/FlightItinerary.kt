package com.example.voyatekgroup.models

import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime

data class FlightItinerary(
    val name: String,
    val code: String,
    val fromLocation: String,
    val toLocation: String,
    val fromTime: LocalDateTime,
    val toTime: LocalDateTime,
    val type: String,
   override val price: String
) : Itinerary {
    val duration = kotlin.run {
        val lDuration = Duration.between(fromTime, toTime)
        val hoursPart = lDuration.toHours() % 24
        val minutesPart = lDuration.toMinutes() % 60
        "${hoursPart}h ${minutesPart}m"
    }
}

val AmericanAirlines = FlightItinerary(
    name = "American Airlines",
    code = "AA-829",
    fromLocation = "LOS",
    toLocation = "SIN",
    fromTime = LocalDateTime.of(2024, 7, 20, 8, 35, 0),
    toTime = LocalDateTime.of(2024, 7, 20, 9, 55, 0),
    type = "Direct",
    price = "₦123,450.00"
)