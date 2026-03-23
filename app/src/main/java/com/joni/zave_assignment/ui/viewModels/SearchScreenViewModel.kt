package com.joni.zave_assignment.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.places.api.Places
import com.joni.zave_assignment.domain.models.Place
import com.joni.zave_assignment.domain.models.UserLocation

import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import com.joni.zave_assignment.utils.Result as Result


data class SearchResultsUiState(
    val query: String = "",
    val stores: List<Place> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isOffline: Boolean = false,
    val userLocation: UserLocation? = null
)

class SearchScreenViewModel(private val placesRepository : NearbySearchRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchResultsUiState>(SearchResultsUiState())
    val uiState: StateFlow<SearchResultsUiState> = _uiState


    fun loadResults(
        query: String,
        lat: Double,
        lng: Double,
        customRadiusKm: Int? = null
    ) {
        val location = UserLocation(lat, lng)
        _uiState.update { it.copy(query = query, userLocation = location, isLoading = true, error = null) }

        // Always show cached data immediately
        placesRepository.getCachedStores(query)
            .onEach { cached ->
                if (cached.isNotEmpty()) {
                    _uiState.update { state ->
                        state.copy(stores = cached.withDistances(location), isOffline = true)
                    }
                }
            }
            .launchIn(viewModelScope)

        // Fetch fresh from network
        viewModelScope.launch {
            val radiusKm = customRadiusKm ?: 300 //remoteConfigRepository.getValues().defaultRadiusKm
            when (val result = placesRepository.getNearbyPlaces(lat, lng, radiusKm * 1000, query)) {
                is Result.Success -> {
                    val sorted = result.data.withDistances(location)
                    _uiState.update { it.copy(stores = sorted, isLoading = false, isOffline = false, error = null) }
                }
                is Result.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                Result.Loading -> Unit
            }
        }
    }

    fun clearError() = _uiState.update { it.copy(error = null) }

    private fun List<Place>.withDistances(origin: UserLocation): List<Place> =
        map { store ->
            store.copy(distanceMeters = haversineMeters(origin.lat, origin.lng, store.lat, store.lng))
        }.sortedBy { it.distanceMeters }

    private fun haversineMeters(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val r = 6_371_000.0
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).let { it * it } +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).let { it * it }
        return (2 * r * atan2(sqrt(a), sqrt(1 - a))).toFloat()
    }

}