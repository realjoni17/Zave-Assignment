package com.joni.zave_assignment.data.repositories

import android.util.Log
import com.joni.zave_assignment.data.remote.PlaceService
import com.joni.zave_assignment.domain.models.Place
import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import com.joni.zave_assignment.mappers.toDomain

class NearbySearchRepositoryImpl(
    private val placeService : PlaceService
) : NearbySearchRepository {

    override suspend fun getNearbyPlaces(
        lat: Double,
        lng: Double,
        radiusMeters: Int,
        keyword: String
    ): List<Place> {
        val result = placeService.nearBySearch(
            location = "$lat,$lng",
            radius = radiusMeters,
            keyword = keyword,
            apiKey = ""
        )
        Log.d("TAG", "getNearbyPlaces: ${result.results.size}")
        return result.toDomain()
    }
}