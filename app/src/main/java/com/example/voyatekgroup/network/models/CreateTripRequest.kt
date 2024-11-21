package com.example.voyatekgroup.network.models

import com.example.voyatekgroup.network.serializers.DateSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class CreateTripRequest(
    val name: String,
    val imageUrl: String,
    val style: String,
    val description: String,
    val location: String,

    @Serializable(DateSerializer::class)
    val startDate: LocalDate,

    @Serializable(DateSerializer::class)
    val endDate: LocalDate,
)