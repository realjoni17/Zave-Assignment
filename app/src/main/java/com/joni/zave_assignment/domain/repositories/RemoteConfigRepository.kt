package com.joni.zave_assignment.domain.repositories

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.joni.zave_assignment.domain.models.RemoteConfigValues
import kotlinx.coroutines.tasks.await


interface RemoteConfigRepository {
    suspend fun fetchAndActivate(): Boolean

    fun getValues(): RemoteConfigValues
}