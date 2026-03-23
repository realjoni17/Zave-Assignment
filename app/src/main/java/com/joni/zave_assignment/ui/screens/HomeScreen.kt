
import android.Manifest
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MyLocation

import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.joni.zave_assignment.domain.models.UserLocation
import com.joni.zave_assignment.ui.theme.DividerColor
import com.joni.zave_assignment.ui.theme.SlateGray
import com.joni.zave_assignment.ui.theme.SurfaceGray
import com.joni.zave_assignment.ui.theme.Teal500
import com.joni.zave_assignment.ui.theme.Teal600
import com.joni.zave_assignment.ui.theme.TextPrimary
import com.joni.zave_assignment.ui.viewModels.HomeUiState


// ── Brand colours (Figma) ────────────────────────────────────────────────────



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    onQueryChange: (String) -> Unit,
    onFetchLocation: () -> Unit,
    onSearch: (String, UserLocation) -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(locationPermissions.allPermissionsGranted) {
        if (locationPermissions.allPermissionsGranted) onFetchLocation()
    }

    val displayName = state.userDisplayName.ifBlank { "there" }
    val greeting = remember {
        val hour = java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)
        when {
            hour < 12 -> "Good morning,"
            hour < 17 -> "Good afternoon,"
            else      -> "Good evening,"
        }
    }

    Scaffold(containerColor = SurfaceGray) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            // ── Header ───────────────────────────────────────────────────────
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 24.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            if (state.userPhotoUrl != null) {
                                AsyncImage(
                                    model = state.userPhotoUrl,
                                    contentDescription = "Avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Text(
                                    text = displayName.first().uppercaseChar().toString(),
                                    fontWeight = FontWeight.Bold,
                                    color = Teal500,
                                    fontSize = 18.sp
                                )
                            }
                        }
                        Column {
                            Text(
                                text = greeting,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = SlateGray
                            )
                            Text(
                                text = displayName,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SurfaceGray)
                            .clickable { onNavigateToSettings() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            modifier = Modifier.size(20.dp),
                            tint = TextPrimary
                        )
                    }
                }
            }

            // ── Banner (Remote Config) ────────────────────────────────────────
            item {
                if (state.remoteConfig.bannerMessage.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .height(191.dp)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Brush.verticalGradient(listOf(Teal500, Teal600)))
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .fillMaxWidth()
                                .background(
                                    Brush.verticalGradient(
                                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.65f))
                                    )
                                )
                                .padding(horizontal = 16.dp, vertical = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "Special Offer",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = state.remoteConfig.bannerMessage,
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }

            // ── Search bar + CTA ─────────────────────────────────────────────
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(12.dp))
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = SlateGray
                        )
                        androidx.compose.foundation.text.BasicTextField(
                            value = state.searchQuery,
                            onValueChange = onQueryChange,
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextPrimary
                            ),
                            decorationBox = { inner ->
                                if (state.searchQuery.isEmpty()) {
                                    Text(
                                        text = "Search for products or categories…",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = SlateGray
                                    )
                                }
                                inner()
                            }
                        )
                        if (state.isLocating) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp,
                                color = Teal500
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.MyLocation,
                                contentDescription = "Use my location",
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        if (locationPermissions.allPermissionsGranted) onFetchLocation()
                                        else locationPermissions.launchMultiplePermissionRequest()
                                    },
                                tint = if (state.location != null) Teal500 else SlateGray
                            )
                        }
                    }

                    AnimatedVisibility(visible = state.locationError != null) {
                        Text(
                            text = state.locationError ?: "",
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (state.searchQuery.isNotBlank()) Teal500
                                else Teal500.copy(alpha = 0.45f)
                            )
                            .clickable(enabled = state.searchQuery.isNotBlank()) {
                                val loc = state.location
                                if (state.searchQuery.isNotBlank() && loc != null) {
                                    onSearch(state.searchQuery.trim(), loc)
                                } else if (!locationPermissions.allPermissionsGranted) {
                                    locationPermissions.launchMultiplePermissionRequest()
                                }
                            }
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.MyLocation,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                        Text(
                            text = when {
                                state.location == null && !locationPermissions.allPermissionsGranted -> "Allow location to search"
                                state.location == null -> "Detecting location…"
                                else -> "Find Nearby Stores"
                            },
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // ── Featured category chip ────────────────────────────────────────
            item {
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(Teal500.copy(alpha = 0.12f))
                            .border(BorderStroke(1.dp, Teal500.copy(alpha = 0.3f)), RoundedCornerShape(20.dp))
                            .clickable {
                                val loc = state.location
                                if (loc != null) onSearch(state.remoteConfig.featuredCategory, loc)
                            }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = "Featured: ${state.remoteConfig.featuredCategory}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Teal600
                        )
                    }
                }
            }

            // ── Recent searches ───────────────────────────────────────────────
            if (state.recentSearches.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 8.dp, bottom = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Recent Searches",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Clear",
                            fontSize = 13.sp,
                            color = Teal500,
                            modifier = Modifier.clickable { }
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(12.dp))
                    ) {
                        state.recentSearches.forEachIndexed { index, search ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onQueryChange(search.query)
                                        val loc = state.location
                                        if (loc != null) onSearch(search.query, loc)
                                    }
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(SurfaceGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.History,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp),
                                        tint = SlateGray
                                    )
                                }
                                Text(
                                    text = search.query,
                                    modifier = Modifier.weight(1f),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = TextPrimary
                                )
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remove",
                                    modifier = Modifier.size(16.dp),
                                    tint = SlateGray
                                )
                            }
                            if (index < state.recentSearches.lastIndex) {
                                HorizontalDivider(color = DividerColor)
                            }
                        }
                    }
                }
            }
        }
    }
}
