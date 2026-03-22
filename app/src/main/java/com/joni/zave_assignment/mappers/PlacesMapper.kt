package com.joni.zave_assignment.mappers

import com.joni.zave_assignment.data.dto.PlacesDto
import com.joni.zave_assignment.domain.models.Place


fun PlacesDto.toDomain() : List<Place>{
     return results.map {
         Place(
           placeId = it.place_id,
           name = it.name,
           address = it.vicinity,
           lat = it.geometry.location.lat,
           lng = it.geometry.location.lng,
           rating = it.rating.toFloat(),
           userRatingsTotal = it.user_ratings_total,
           types = it.types,
           iconUrl = it.icon,
           isOpen = it.opening_hours.open_now
       )
   }

}