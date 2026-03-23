package com.joni.zave_assignment.domain.models

data class RemoteConfigValues(
    val defaultRadiusKm: Int = 100,
    val featuredCategory: String = "electronics",
    val bannerMessage: String = "Explore deals near you!"
)
