package com.example.voyatekgroup.models

import com.example.voyatekgroup.network.serializers.DateSerializer
import kotlinx.serialization.Serializable
import java.time.Duration
import java.time.LocalDate

@Serializable
data class Trip(
    val id: String,
    val name: String,
    val imageUrl: String,
    val style: String,
    val description: String,
    val location: String,

    @Serializable(DateSerializer::class)
    val startDate: LocalDate,

    @Serializable(DateSerializer::class)
    val endDate: LocalDate,
) {
    val duration = kotlin.run {
        val days =  Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays()
        "$days Days"
    }
}

val exampleTrip = Trip(
    id = "",
    name = "Bahamas Family Trip",
    imageUrl = "https://www.google.com/#q=verterem",
    style = "Solo",
    description = "Enjoyment no go kill person",
    location = "New York,  United States of America",
    startDate =LocalDate.now().plusDays(4),
    endDate =LocalDate.now().plusDays(20)
)