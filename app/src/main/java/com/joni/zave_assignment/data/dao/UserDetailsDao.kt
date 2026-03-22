package com.joni.zave_assignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joni.zave_assignment.data.entities.UserDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {
    @Query("SELECT * FROM user_details WHERE id = 0")
    fun getUserPrefs(): Flow<UserDetails?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(prefs: UserDetails)

    @Query("UPDATE user_details SET lastKnownLat = :lat, lastKnownLng = :lng WHERE id = 0")
    suspend fun updateLocation(lat: Double, lng: Double)

    @Query("UPDATE user_details SET customRadiusKm = :radius WHERE id = 0")
    suspend fun updateRadius(radius: Int?)

    @Query("DELETE FROM user_details")
    suspend fun clear()
}
