package com.joni.zave_assignment.data.repositories

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.joni.zave_assignment.data.dao.CachedStoreDao
import com.joni.zave_assignment.data.dao.SearchHistoryDao
import com.joni.zave_assignment.data.entities.CachedStoreEntity
import com.joni.zave_assignment.data.entities.SearchHistoryEntity
import com.joni.zave_assignment.data.remote.PlaceService
import com.joni.zave_assignment.domain.models.Place
import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import com.joni.zave_assignment.mappers.toDomain
import com.joni.zave_assignment.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NearbySearchRepositoryImpl(
    private val placeService : PlaceService,
    private val cachedStoreDao : CachedStoreDao,
    private val searchHistoryDao : SearchHistoryDao
) : NearbySearchRepository {

    private val gson = Gson()

   override fun getCachedStores(query: String): Flow<List<Place>> =
        cachedStoreDao.getStoresForQuery(query).map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun getNearbyPlaces(
        lat: Double,
        lng: Double,
        radiusMeters: Int,
        keyword: String
    ): Result<List<Place>> = try {
        val result = placeService.nearBySearch(
            location = "$lat,$lng",
            radius = radiusMeters,
            keyword = keyword,
            apiKey = ""
        )
        Log.d("TAG", "getNearbyPlaces: ${result.results.size}")
        // Cache results
        cachedStoreDao.clearForQuery(keyword)
        cachedStoreDao.insertAll(result.toDomain().map { it.toEntity(keyword) })

        // Evict cache older than 1 day
        cachedStoreDao.evictOlderThan(System.currentTimeMillis() - 86_400_000)

        // Save search history
        searchHistoryDao.insertSearch(SearchHistoryEntity(query = keyword))
        searchHistoryDao.pruneOldSearches()
        Result.Success(result.toDomain())
    } catch (e : Exception){
        Result.Error("Failled to fetch data ${e.message}")
    }

    override fun getRecentSearches() = searchHistoryDao.getRecentSearches()

    private fun CachedStoreEntity.toDomain(): Place {
        val typeToken = object : TypeToken<List<String>>() {}.type
        return Place(
            placeId = placeId,
            name = name,
            address = address,
            lat = lat,
            lng = lng,
            rating = rating,
            userRatingsTotal = userRatingsTotal,
            types = gson.fromJson(types, typeToken),
            iconUrl = iconUrl,
            isOpen = isOpen
        )
    }

    private fun Place.toEntity(query: String) = CachedStoreEntity(
        placeId = placeId,
        name = name,
        address = address,
        lat = lat,
        lng = lng,
        rating = rating,
        userRatingsTotal = userRatingsTotal,
        types = gson.toJson(types),
        iconUrl = iconUrl,
        isOpen = isOpen,
        searchQuery = query
    )
}