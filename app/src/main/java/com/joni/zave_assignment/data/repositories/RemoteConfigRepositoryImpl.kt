package com.joni.zave_assignment.data.repositories

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.joni.zave_assignment.domain.models.RemoteConfigValues
import com.joni.zave_assignment.domain.repositories.RemoteConfigRepository
import kotlinx.coroutines.tasks.await

class RemoteConfigRepositoryImpl(
    private val remoteConfig: FirebaseRemoteConfig
) : RemoteConfigRepository{
    override suspend fun fetchAndActivate(): Boolean = try {
        remoteConfig.fetchAndActivate().await()
    } catch (e: Exception) {
        false
    }

    override fun getValues(): RemoteConfigValues = RemoteConfigValues(
    defaultRadiusKm = remoteConfig.getLong("default_radius_km").toInt().coerceIn(1, 50),
    featuredCategory = remoteConfig.getString("featured_category").ifBlank { "electronics" },
    bannerMessage = remoteConfig.getString("banner_message").ifBlank { "Explore deals near you!" }
    )

}