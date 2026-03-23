package com.joni.zave_assignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joni.zave_assignment.data.entities.CachedStoreEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedStoreDao {
    @Query("SELECT * FROM cached_stores WHERE searchQuery = :query ORDER BY name ASC")
    fun getStoresForQuery(query: String): Flow<List<CachedStoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(stores: List<CachedStoreEntity>)

    @Query("DELETE FROM cached_stores WHERE searchQuery = :query")
    suspend fun clearForQuery(query: String)

    @Query("DELETE FROM cached_stores WHERE cachedAt < :cutoff")
    suspend fun evictOlderThan(cutoff: Long)
}