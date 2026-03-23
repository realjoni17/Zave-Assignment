package com.joni.zave_assignment.ui.screens


import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerInfoWindow
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.joni.zave_assignment.domain.models.Place
import com.joni.zave_assignment.ui.theme.BgSurface
import com.joni.zave_assignment.ui.theme.ClosedRed
import com.joni.zave_assignment.ui.theme.DividerColor
import com.joni.zave_assignment.ui.theme.OpenGreen
import com.joni.zave_assignment.ui.theme.Teal500
import com.joni.zave_assignment.ui.theme.TealDark
import com.joni.zave_assignment.ui.theme.TealLight
import com.joni.zave_assignment.ui.theme.TextMuted
import com.joni.zave_assignment.ui.theme.TextPrimary
import com.joni.zave_assignment.ui.viewModels.SearchResultsUiState

import kotlin.math.roundToInt



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultsScreen(
    state: SearchResultsUiState,
    query: String,
    lat: Double,
    lng: Double,
    onLoad: (String, Double, Double, Int?) -> Unit,
    onBack: () -> Unit,
    onClearError: () -> Unit
) {
    val context = LocalContext.current
    var showMap by remember { mutableStateOf(false) }
    var selectedStore by remember { mutableStateOf<Place?>(null) }
    var activeFilter by remember { mutableStateOf("Nearest") }

    LaunchedEffect(query, lat, lng) {
        onLoad(query, lat, lng, null)
    }

    Scaffold(
        containerColor = BgSurface,
        topBar = {
            Column {
                // Header row — Figma Header1
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BgSurface)
                        .padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(TealLight)
                            .padding(horizontal = 16.dp, vertical = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { onBack() },
                            tint = TealDark
                        )
                        Text(
                            text = query,
                            color = TextPrimary,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (showMap) Icons.Default.CloudOff else Icons.Default.Map,
                            contentDescription = "Toggle map",
                            modifier = Modifier
                                .size(20.dp)
                                .clickable { showMap = !showMap },
                            tint = TealDark
                        )
                    }
                }

                // Filter chips — Figma HorizontalBorder
                val filters = listOf("Nearest", "Top Rated", "Open Now", "Offers Delivery")
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BgSurface)
                        .padding(bottom = 8.dp)
                ) {
                    items(filters.size) { i ->
                        val label = filters[i]
                        val isActive = activeFilter == label
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isActive) Teal500 else TealLight)
                                .clickable { activeFilter = label }
                                .padding(horizontal = 12.dp, vertical = 7.dp)
                        ) {
                            Text(
                                text = label,
                                color = if (isActive) Color.White else TealDark,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                // Offline banner
                if (state.isOffline) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFFFF3CD))
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.CloudOff, null, Modifier.size(14.dp), tint = Color(0xFF856404))
                        Text("Showing cached results", fontSize = 12.sp, color = Color(0xFF856404))
                    }
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            if (showMap && state.userLocation != null) {
                MapView(
                    stores = state.stores,
                    userLat = state.userLocation.lat,
                    userLng = state.userLocation.lng,
                    selectedStore = selectedStore,
                    onStoreSelected = { selectedStore = it }
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (state.isLoading && state.stores.isEmpty()) {
                        items(4) { StoreCardSkeleton() }
                    } else if (state.stores.isEmpty() && !state.isLoading) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(top = 80.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text("No stores found", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                                    Text("Try a different search or increase your radius", fontSize = 14.sp, color = TextMuted)
                                }
                            }
                        }
                    } else {
                        itemsIndexed(state.stores) { _, store ->
                            AnimatedVisibility(
                                visible = true,
                                enter = fadeIn() + slideInVertically(initialOffsetY = { it / 4 })
                            ) {
                                StoreCard(
                                    store = store,
                                    onViewMap = {
                                        val uri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${store.lat},${store.lng}&query_place_id=${store.placeId}")
                                        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                                    },
                                    onDirections = {
                                        val uri = Uri.parse("google.navigation:q=${store.lat},${store.lng}")
                                        context.startActivity(Intent(Intent.ACTION_VIEW, uri))
                                    }
                                )
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(visible = state.error != null, modifier = Modifier.align(Alignment.BottomCenter)) {
                state.error?.let { error ->
                    Log.d("TAG", "SearchResultsScreen: $error")
                    Snackbar(modifier = Modifier.padding(16.dp), action = { TextButton(onClick = onClearError) { Text("Dismiss") } }) { Text(error) }
                }
            }

            if (state.isLoading && state.stores.isNotEmpty()) {
                LinearProgressIndicator(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart), color = Teal500, trackColor = TealLight)
            }
        }
    }
}

@Composable
fun StoreCard(store: Place, onViewMap: () -> Unit, onDirections: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = store.iconUrl,
                contentDescription = store.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color(0xFFF1F5F7))
            )
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    store.name?.let { Text(it, color = TextPrimary, fontSize = 16.sp, modifier = Modifier.weight(1f, fill = false)) }
                    store.rating?.let { rating ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, null, Modifier.size(14.dp), tint = Color(0xFFF59E0B))
                            Text("%.1f".format(rating), color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                ) {
                    val typeLabel = store.types?.firstOrNull()?.replace("_", " ")?.replaceFirstChar { it.uppercase() } ?: "Store"
                    Text(typeLabel, color = TextMuted, fontSize = 13.sp)
                    Text("•", color = TextMuted, fontSize = 10.sp)
                    store.distanceMeters?.let { Text(formatDistance(it), color = TextMuted, fontSize = 13.sp) }
                }
                store.isOpen?.let { open ->
                    Text(
                        text = if (open) "Open now" else "Closed",
                        color = if (open) OpenGreen else ClosedRed,
                        fontSize = 13.sp, fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.5f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(TealLight)
                    .clickable { onViewMap() }
                    .padding(10.dp)
            ) {
                Icon(Icons.Default.Map, null, Modifier.size(16.dp), tint = TealDark)
                Text("View on Map", color = TealDark, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(0.5f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Teal500)
                    .clickable { onDirections() }
                    .padding(10.dp)
            ) {
                Icon(Icons.Default.Navigation, null, Modifier.size(16.dp), tint = Color.White)
                Text("Directions", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun StoreCardSkeleton() {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Surface(Modifier.size(88.dp), color = Color(0xFFF1F5F7), shape = RoundedCornerShape(6.dp)) {}
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(Modifier
                    .fillMaxWidth(0.6f)
                    .height(16.dp), color = Color(0xFFF1F5F7), shape = RoundedCornerShape(4.dp)) {}
                Surface(Modifier
                    .fillMaxWidth(0.4f)
                    .height(12.dp), color = Color(0xFFF1F5F7), shape = RoundedCornerShape(4.dp)) {}
                Surface(Modifier
                    .fillMaxWidth(0.3f)
                    .height(12.dp), color = Color(0xFFF1F5F7), shape = RoundedCornerShape(4.dp)) {}
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Surface(Modifier
                .weight(0.5f)
                .height(38.dp), color = Color(0xFFF1F5F7), shape = RoundedCornerShape(6.dp)) {}
            Surface(Modifier
                .weight(0.5f)
                .height(38.dp), color = Color(0xFFF1F5F7), shape = RoundedCornerShape(6.dp)) {}
        }
    }
}

@Composable
fun MapView(stores: List<Place>, userLat: Double, userLng: Double, selectedStore: Place?, onStoreSelected: (Place?) -> Unit) {
    val userLatLng = LatLng(userLat, userLng)
    val cameraPositionState = rememberCameraPositionState { position = CameraPosition.fromLatLngZoom(userLatLng, 13f) }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(zoomControlsEnabled = true, myLocationButtonEnabled = true),
        properties = MapProperties(isMyLocationEnabled = true)
    ) {
        stores.forEach { store ->
            val pos = store.lat?.let { store.lng?.let { longitude -> LatLng(it, longitude) } }
            val isSelected = selectedStore?.placeId == store.placeId
            pos?.let {
                MarkerInfoWindow(
                    state = rememberMarkerState(position = it),
                    title = store.name, snippet = store.address,
                    icon = BitmapDescriptorFactory.defaultMarker(if (isSelected) BitmapDescriptorFactory.HUE_AZURE else BitmapDescriptorFactory.HUE_RED),
                    onClick = { onStoreSelected(if (isSelected) null else store); false }
                )
            }
        }
    }
    selectedStore?.let { store ->
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                store.name?.let { Text(it, fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = TextPrimary) }
                store.address?.let { Text(it, fontSize = 13.sp, color = TextMuted) }
                store.distanceMeters?.let { Text(formatDistance(it), fontSize = 13.sp, color = Teal500, fontWeight = FontWeight.Medium) }
            }
        }
    }
}

private fun formatDistance(meters: Float): String = when {
    meters < 1000 -> "${meters.roundToInt()} m"
    else -> "${"%.1f".format(meters / 1000)} km"
}
