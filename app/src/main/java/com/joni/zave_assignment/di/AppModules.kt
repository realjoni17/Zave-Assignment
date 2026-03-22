package com.joni.zave_assignment.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.joni.zave_assignment.data.dao.UserDetailsDao
import com.joni.zave_assignment.data.database.ZaveDatabase
import com.joni.zave_assignment.data.remote.PlaceService
import com.joni.zave_assignment.data.repositories.FirebaseAuthRepositoryImpl
import com.joni.zave_assignment.data.repositories.LocationRepositoryImpl
import com.joni.zave_assignment.data.repositories.NearbySearchRepositoryImpl
import com.joni.zave_assignment.domain.repositories.FirebaseAuthRepository
import com.joni.zave_assignment.domain.repositories.LocationRepository
import com.joni.zave_assignment.domain.repositories.NearbySearchRepository
import com.joni.zave_assignment.ui.viewModels.FirebaseAuthViewModel
import com.joni.zave_assignment.ui.viewModels.LocationViewModel
import com.joni.zave_assignment.ui.viewModels.PlacesViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val module = module {
    single { provideDataBase(get()) }
    single { provideUserDetailsDao(get()) }
    single { providePlacesApi() }
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }

  single<NearbySearchRepository> { NearbySearchRepositoryImpl(get()) }
  single<FirebaseAuthRepository>{ FirebaseAuthRepositoryImpl(get(),get()) }
    single<LocationRepository>{ LocationRepositoryImpl(get(),get()) }


    viewModel { PlacesViewModel(get()) }
    viewModel { FirebaseAuthViewModel(get()) }
    viewModel { LocationViewModel(get()) }
}

//fun providesOkHttpClient() : OkHttpClient = OkHttpClient.Builder().addInterceptor()

fun providePlacesApi(): PlaceService = Retrofit.Builder()
    .baseUrl("https://maps.googleapis.com/maps/api/place/")
    //.client(client)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(PlaceService::class.java)

fun provideDataBase( context : Context) : ZaveDatabase = Room.databaseBuilder(
    context, ZaveDatabase::class.java, "zave_db"
).fallbackToDestructiveMigration(false).build()

fun provideUserDetailsDao(db : ZaveDatabase) : UserDetailsDao = db.userDetailsDao()