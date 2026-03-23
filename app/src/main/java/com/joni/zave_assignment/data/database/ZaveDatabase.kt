package com.joni.zave_assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joni.zave_assignment.data.dao.CachedStoreDao
import com.joni.zave_assignment.data.dao.SearchHistoryDao
import com.joni.zave_assignment.data.dao.UserDetailsDao
import com.joni.zave_assignment.data.entities.CachedStoreEntity
import com.joni.zave_assignment.data.entities.SearchHistoryEntity
import com.joni.zave_assignment.data.entities.UserDetails


@Database(
    entities = [UserDetails::class, CachedStoreEntity::class, SearchHistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ZaveDatabase : RoomDatabase() {

    abstract fun userDetailsDao(): UserDetailsDao

    abstract fun cachedStoreDao() : CachedStoreDao

    abstract fun searchHistoryDao() : SearchHistoryDao

}
