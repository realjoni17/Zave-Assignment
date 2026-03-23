package com.joni.zave_assignment.domain.repositories

import com.joni.zave_assignment.data.entities.SearchHistoryEntity
import com.joni.zave_assignment.domain.models.Place
import kotlinx.coroutines.flow.Flow
import com.joni.zave_assignment.utils.Result as Result

interface NearbySearchRepository {

    suspend fun getNearbyPlaces(
        lat: Double,
        lng: Double,
        radiusMeters: Int,
        keyword: String
    ) : Result<List<Place>>

    fun getRecentSearches(): Flow<List<SearchHistoryEntity>>
    fun getCachedStores(query: String): Flow<List<Place>>

}