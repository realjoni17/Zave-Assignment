package com.joni.zave_assignment.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_details")
data class UserDetails(
    @PrimaryKey val id: Int = 0,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
    val lastKnownLat: Double?,
    val lastKnownLng: Double?,
    val customRadiusKm: Int?
)
