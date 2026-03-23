package com.joni.zave_assignment.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.zave_assignment.domain.models.SearchQuery
import com.joni.zave_assignment.domain.models.UserLocation
import com.joni.zave_assignment.domain.repositories.LocationRepository
import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.joni.zave_assignment.utils.Result as Result


data class HomeUiState(
    val searchQuery: String = "",
    val recentSearches: List<SearchQuery> = emptyList(),
   // val remoteConfig: RemoteConfigValues = RemoteConfigValues(),
    val location: UserLocation? = null,
    val isLocating: Boolean = false,
    val locationError: String? = null,
    val userPhotoUrl: String? = null,
    val userDisplayName: String = "",
)

class HomeScreenViewModel(private val locationRepository: LocationRepository,
    private  val placesRepository: NearbySearchRepository) : ViewModel(){

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
      //  loadRemoteConfig()
        //observeRecentSearches()
    }

/*    private fun loadRemoteConfig() {
        viewModelScope.launch {
            remoteConfigRepository.fetchAndActivate()
            _uiState.update { it.copy(remoteConfig = remoteConfigRepository.getValues()) }
        }
    }*/

 /*   private fun observeRecentSearches() {
        placesRepository.getRecentSearches()
            .onEach { entities ->
                _uiState.update { state ->
                    state.copy(recentSearches = entities.map { SearchQuery(it.id, it.query, it.timestamp) })
                }
            }
            .launchIn(viewModelScope)
    }*/

    fun onSearchQueryChange(query: String) = _uiState.update { it.copy(searchQuery = query) }

    fun fetchLocation() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLocating = true, locationError = null) }
            when (val result = locationRepository.getCurrentLocation()) {
                is Result.Success -> _uiState.update { it.copy(location = result.data, isLocating = false) }
                is Result.Error -> _uiState.update { it.copy(locationError = result.message, isLocating = false) }
                Result.Loading -> Unit
            }
        }
    }

}