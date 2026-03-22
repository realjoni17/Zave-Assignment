package com.joni.zave_assignment.data.remote

import com.joni.zave_assignment.data.dto.PlacesDto
import retrofit2.http.GET
import retrofit2.http.Query


interface PlaceService {

    @GET("nearbysearch/json")
    suspend fun nearBySearch(
        @Query("location") location: String,    // "lat,lng"
        @Query("radius") radius: Int,           // metres
        @Query("keyword") keyword: String,
        @Query("type") type: String = "store",
        @Query("key") apiKey: String) : PlacesDto

}

//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=12.935,77.614&radius=3000&type=store&keyword=electronics&key=Api_key