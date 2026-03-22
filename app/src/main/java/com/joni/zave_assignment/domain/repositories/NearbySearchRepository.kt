package com.joni.zave_assignment.domain.repositories

import com.joni.zave_assignment.domain.models.Place

interface NearbySearchRepository {

    suspend fun getNearbyPlaces(
        lat: Double,
        lng: Double,
        radiusMeters: Int,
        keyword: String
    ) : List<Place>

}