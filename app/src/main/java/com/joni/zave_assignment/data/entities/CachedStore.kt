package com.joni.zave_assignment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_stores")
data class CachedStoreEntity(
    @PrimaryKey val placeId: String,
    val name: String?,
    val address: String?,
    val lat: Double?,
    val lng: Double?,
    val rating: Float?,
    val userRatingsTotal: Int?,
    val types: String,
    val iconUrl: String?,
    val isOpen: Boolean?,
    val searchQuery: String,
    val cachedAt: Long = System.currentTimeMillis()
)