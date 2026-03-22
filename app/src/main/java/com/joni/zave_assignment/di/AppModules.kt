package com.joni.zave_assignment.di

import com.joni.zave_assignment.data.remote.PlaceService
import com.joni.zave_assignment.data.repositories.NearbySearchRepositoryImpl
import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import com.joni.zave_assignment.ui.viewModels.PlacesViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val module = module {
    single { providePlacesApi() }

  single<NearbySearchRepository> { NearbySearchRepositoryImpl(get()) }



    viewModel { PlacesViewModel(get()) }
}

//fun providesOkHttpClient() : OkHttpClient = OkHttpClient.Builder().addInterceptor()

fun providePlacesApi(): PlaceService = Retrofit.Builder()
    .baseUrl("https://maps.googleapis.com/maps/api/place/")
    //.client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(PlaceService::class.java)