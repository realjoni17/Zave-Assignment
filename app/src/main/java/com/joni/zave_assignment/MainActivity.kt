package com.joni.zave_assignment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient
import com.joni.zave_assignment.ui.theme.ZaveassignmentTheme
import com.joni.zave_assignment.ui.viewModels.PlacesViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {




    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
      //  Places.initialize(this, "AIzaSyDbUf4PEFoOI9x2klHy9WkxPL5QZ6mqDX4")
     //   initializePlacesClient(this,"AIzaSyDbUf4PEFoOI9x2klHy9WkxPL5QZ6mqDX4")
        super.onCreate(savedInstanceState)
         val viewModel = viewModels<PlacesViewModel>()

        enableEdgeToEdge()
        setContent {
            ZaveassignmentTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Greeting(

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: PlacesViewModel = koinViewModel()) {
    viewModel.getNearbySearchPlace(12.935,77.614,3000,"electronics")
    val temp = viewModel.places.collectAsState()
    Log.d("Joni", "Greeting: ${temp.value.size}")
    LazyColumn() {
        temp.value.forEach {
            items(temp.value){ jio->
                Text(jio.name)
            }

        }
    }
    Text(
        text = temp.value.size.toString(),
    )
}

