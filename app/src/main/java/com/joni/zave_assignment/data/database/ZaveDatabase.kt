package com.joni.zave_assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joni.zave_assignment.data.dao.UserDetailsDao
import com.joni.zave_assignment.data.entities.UserDetails


@Database(
    entities = [UserDetails::class],
    version = 1,
    exportSchema = false
)
abstract class ZaveDatabase : RoomDatabase() {

    abstract fun userDetailsDao(): UserDetailsDao

}
