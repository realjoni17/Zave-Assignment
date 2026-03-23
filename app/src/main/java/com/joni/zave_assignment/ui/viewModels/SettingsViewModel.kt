package com.joni.zave_assignment.ui.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.zave_assignment.data.dao.UserDetailsDao
import com.joni.zave_assignment.domain.models.RemoteConfigValues
import com.joni.zave_assignment.domain.repositories.FirebaseAuthRepository
import com.joni.zave_assignment.domain.repositories.LocationRepository
import com.joni.zave_assignment.domain.repositories.RemoteConfigRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val customRadiusKm: Int? = null,
    val useAutoLocation: Boolean = true,
    val remoteConfig: RemoteConfigValues = RemoteConfigValues(),
    val isSigningOut: Boolean = false
)


class SettingsViewModel (
    private val authRepository: FirebaseAuthRepository,
    private val locationRepository: LocationRepository,
    private val remoteConfigRepository: RemoteConfigRepository,
    private val userPrefsDao: UserDetailsDao
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(remoteConfig = remoteConfigRepository.getValues()) }
        viewModelScope.launch {
            locationRepository.getLastLocation()
                .onEach { prefs ->
                    prefs?.let {
                        _uiState.update { state ->
                            state.copy(
                                displayName = it.displayName,
                                email = it.email,
                                photoUrl = it.photoUrl,
                                customRadiusKm = it.customRadiusKm
                            )
                        }
                    }
                }
        }


    }

    fun setCustomRadius(km: Int?) {
        viewModelScope.launch {
            userPrefsDao.updateRadius(km)
            _uiState.update { it.copy(customRadiusKm = km) }
        }
    }

    fun setUseAutoLocation(enabled: Boolean) = _uiState.update { it.copy(useAutoLocation = enabled) }

    fun signOut(onComplete: () -> Unit) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSigningOut = true) }
            authRepository.signOut()
            onComplete()
        }
    }
}
