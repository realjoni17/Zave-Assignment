package com.joni.zave_assignment.ui.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.zave_assignment.domain.models.Place

import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.annotation.KoinViewModelScopeApi
import org.koin.viewmodel.emptyState



class PlacesViewModel(private val repository : NearbySearchRepository) : ViewModel() {

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places


    fun getNearbySearchPlace(
        lat : Double,
        lng : Double,
        radiusMeter : Int,
        keyWord : String

    ){
        viewModelScope.launch {
            val data =  repository.getNearbyPlaces(
                lat = lat,
                lng = lng,
                radiusMeters = radiusMeter,
                keyWord
            )
            _places.value = data

        }

    }

}