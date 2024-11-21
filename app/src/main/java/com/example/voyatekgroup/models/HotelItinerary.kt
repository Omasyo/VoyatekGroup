package com.example.voyatekgroup.models

import java.time.LocalDate

data class HotelItinerary(
    val name: String,
    val address: String,
    val imageUrl: String,
    val rating: String,
    val ratingCount: Int,
    val roomSize: String,
    val inDate: LocalDate,
    val outDate: LocalDate,
    override val price: String
) : Itinerary

val RiveraResort = HotelItinerary(
    name = "Rivera Resort, Lekki",
    address = "18, Kenneth Agbakuru Street, Off Access Bank Admiralty Way, Lekki Phase1",
    imageUrl = "https://s3-alpha-sig.figma.com/img/8b34/fa1a/4847cc7d69da82122baadbbb8b61b0e9?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=SZfzFCWj5UNw5xb62n7RUvE-wZc2K8GfGRKUL4yG6QlY4CmypRbhqXM~93MSRggN0YrOmYcjcsYs6p1Sq0VJzLR6L~4Yim20Hd2FG5YgmSLXsaH9dCmtWZ0AAP1ozaqRolWpF5am4YaROyz5fNZ7Xb7Dbt27eT696lcqovfIrg~NGEKqiEWOfPyasfV7yglqXJzKLRd2mWoJxKoshKckixrmIeu65nFwv5dT5VnAfqqlcAY4sKqPLNIO8pzui1zDIk~YrlqAnPz0Z99Z-raXE8Y3eq4X-XVQhBSh~3oqcCGjq~~bTA8-cVdwscJDBDFDUQWey0LOkYGjsAMt06qIaA__",
    rating = "8.5",
    ratingCount = 436,
    roomSize = "King size room",
    inDate = LocalDate.of(2024, 4, 20),
    outDate = LocalDate.of(2024, 4, 29),
    price = "₦123,450.00"
)