package com.joni.zave_assignment.domain.repositories

import com.joni.zave_assignment.data.entities.UserDetails
import com.joni.zave_assignment.utils.Result as Result
import com.joni.zave_assignment.domain.models.UserLocation
import kotlinx.coroutines.flow.Flow

interface LocationRepository{

    suspend fun getCurrentLocation() : Result<UserLocation>

    suspend fun getLastLocation(): Flow<UserDetails?>
}