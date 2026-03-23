package com.joni.zave_assignment.ui.navigation

import AuthScreen
import HomeScreen
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joni.zave_assignment.ui.viewModels.FirebaseAuthViewModel
import com.joni.zave_assignment.ui.viewModels.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun ZaveNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = "home") {

        composable("auth") {
            val vm: FirebaseAuthViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()
            AuthScreen(
                state = state,
                onGoogleSignIn = vm::signInWithGoogle,
                onSignInSuccess = {
                  Toast.makeText(context,"", Toast.LENGTH_LONG).show()

                    navController.navigate("") {
                        popUpTo("Routes.AUTH") { inclusive = true }
                    }
                },
                onClearError = vm::clearError
            )
        }

        composable("home") {
            val vm: HomeScreenViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                state = state,
                onQueryChange = vm::onSearchQueryChange,
                onFetchLocation = vm::fetchLocation,
                onSearch = { query, location ->
                   // navController.navigate(Routes.results(query, location.lat, location.lng))
                },
                onNavigateToSettings = { /*navController.navigate(Routes.SETTINGS)*/ }
            )
        }



    }
}
