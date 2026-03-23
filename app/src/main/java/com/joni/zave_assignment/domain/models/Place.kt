package com.joni.zave_assignment.domain.models

data class Place(
    val placeId: String?,
    val name: String?,
    val address: String?,
    val lat: Double?,
    val lng: Double?,
    val rating: Float?,
    val userRatingsTotal: Int?,
    val types: List<String?>?,
    val iconUrl: String?,
    val isOpen: Boolean?,
    val distanceMeters: Float? = null
)