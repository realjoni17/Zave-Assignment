package com.joni.zave_assignment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
      //  Places.initialize(this, "AIzaSyDbUf4PEFoOI9x2klHy9WkxPL5QZ6mqDX4")
     //   initializePlacesClient(this,"AIzaSyDbUf4PEFoOI9x2klHy9WkxPL5QZ6mqDX4")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZaveassignmentTheme {
            //    Log.d("Joni Sharma is legend", "onCreate: ${abc.result.place.name}")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Joni Sharma",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ZaveassignmentTheme {
        Greeting("Android")
    }
}