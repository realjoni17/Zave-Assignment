package com.joni.zave_assignment.ui.navigation

import AuthScreen
import HomeScreen

import SettingsScreen
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joni.zave_assignment.ui.screens.SearchResultsScreen
import com.joni.zave_assignment.ui.viewModels.FirebaseAuthViewModel
import com.joni.zave_assignment.ui.viewModels.HomeScreenViewModel
import com.joni.zave_assignment.ui.viewModels.SearchScreenViewModel
import com.joni.zave_assignment.ui.viewModels.SettingsViewModel
import org.koin.androidx.compose.koinViewModel


object Routes {
    const val AUTH = "auth"
    const val HOME = "home"
    const val RESULTS = "results/{query}/{lat}/{lng}"
    const val SETTINGS = "settings"

    fun results(query: String, lat: Double, lng: Double) = "results/$query/$lat/$lng"
}

@Composable
fun ZaveNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController = navController, startDestination = Routes.AUTH) {

        composable(Routes.AUTH) {
            val vm: FirebaseAuthViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()
            AuthScreen(
                state = state,
                onGoogleSignIn = vm::signInWithGoogle,
                onSignInSuccess = {
                  Toast.makeText(context,"", Toast.LENGTH_LONG).show()

                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.AUTH) { inclusive = true }
                    }
                },
                onClearError = vm::clearError
            )
        }

        composable(Routes.HOME) {
            val vm: HomeScreenViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()
            HomeScreen(
                state = state,
                onQueryChange = vm::onSearchQueryChange,
                onFetchLocation = vm::fetchLocation,
                onSearch = { query, location ->
                    navController.navigate(Routes.results(query, location.lat, location.lng))
                },
                onNavigateToSettings = { navController.navigate(Routes.SETTINGS) }
            )
        }

        composable(
            route = Routes.RESULTS,
            arguments = listOf(
                navArgument("query") { type = NavType.StringType },
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lng") { type = NavType.FloatType }
            )
        ) { backStack ->
            val vm: SearchScreenViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()
            val query = backStack.arguments?.getString("query") ?: ""
            val lat = backStack.arguments?.getFloat("lat")?.toDouble() ?: 0.0
            val lng = backStack.arguments?.getFloat("lng")?.toDouble() ?: 0.0
            SearchResultsScreen(
                state = state,
                query = query,
                lat = lat,
                lng = lng,
                onLoad = vm::loadResults,
                onBack = { navController.popBackStack() },
                onClearError = vm::clearError
            )
        }

        composable(Routes.SETTINGS) {
            val vm: SettingsViewModel = koinViewModel()
            val state by vm.uiState.collectAsStateWithLifecycle()
            SettingsScreen(
                state = state,
                onRadiusChange = vm::setCustomRadius,
                onAutoLocationChange = vm::setUseAutoLocation,
                onSignOut = {
                    vm.signOut {
                        navController.navigate(Routes.AUTH) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

    }
}
