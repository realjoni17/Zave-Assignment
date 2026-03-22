package com.joni.zave_assignment.data.repositories

import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.joni.zave_assignment.data.dao.UserDetailsDao
import com.joni.zave_assignment.utils.Result as Result
import com.joni.zave_assignment.domain.models.UserLocation
import com.joni.zave_assignment.domain.repositories.LocationRepository
import kotlinx.coroutines.tasks.await

class LocationRepositoryImpl(
    private val context : Context,
    private val userDetailsDao: UserDetailsDao
) : LocationRepository{

    private val fusedLocation = LocationServices.getFusedLocationProviderClient(context)

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override suspend fun getCurrentLocation(): Result<UserLocation>  = try{
        val location = fusedLocation.getCurrentLocation(Priority.PRIORITY_BALANCED_POWER_ACCURACY, null).await()
        if (location != null ){
            userDetailsDao.updateLocation(location.latitude, location.longitude)
            Result.Success(UserLocation(location.latitude,location.longitude))
        } else {
            Result.Error("Could not obtain location")
        }
    }
    catch (e : Exception){
        Result.Error(e.message ?: "Location error", e)
    }

    override suspend fun getLastLocation() =  userDetailsDao.getUserPrefs()

}