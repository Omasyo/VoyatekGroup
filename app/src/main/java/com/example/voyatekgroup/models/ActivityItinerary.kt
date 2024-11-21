package com.example.voyatekgroup.models

import java.time.LocalDateTime

data class ActivityItinerary(
    val name: String,
    val description: String,
    val imageUrl: String,
    val location: String,
    val rating: String,
    val ratingCount: Int,
    val duration: String,
    val activity: Activity,
   override val price: String,
) : Itinerary

data class Activity(
    val name: String,
    val time: LocalDateTime
)

val Museum = ActivityItinerary(
    name = "The Museum of Modern Art",
    description = "Works from Van Gogh to Warhol & beyond plus a sculpture garden, 2 cafes & The modern restaurant",
    imageUrl = "https://s3-alpha-sig.figma.com/img/f358/55a8/1a8b7ba11e7d511216524804f7cf17f4?Expires=1733097600&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=hPaHsCyuj6e-fmKCKkfFy7h71D8z-9lpGXlvM8KnPIZOAUE20ZjOfR4z89Ysr0v3VuP4OkmlE1vfqOV5Q6KNEceE7KI6nOcbOTcx~APJ0K1T8Q~mxhwocxYUuMdcBzQIwUY4leZ65s47mDKzI6E24g9zTjBElpZyO3NXyaljGofcmMS0wDeYnr-ENBjQyUtOzHkYc30hnvQ5-czLaOTDVzxbcRxq3cIs71G4EjtKqFP3VJ1CVP6xIUOS9tnTNEHjuCWuFlk5swBOz~qr6n-5VCWcDjsJ19o~jQoVnQtkFR3gtIiVkhsabkBkcW8ZD-wmlVN6uttKNa-zma3-sZBk~Q__",
    location = "Melbourne, Austrailia",
    rating = "8.5",
    ratingCount = 436,
    duration = "1 hour",
    activity = Activity(
        name = "Day 1 (Activity 1)",
        time = LocalDateTime.of(2024, 3, 19, 10, 30, 0)
    ),
    price = "₦123,450.00"
)