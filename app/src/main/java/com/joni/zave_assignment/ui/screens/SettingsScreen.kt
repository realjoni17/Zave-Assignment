
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.joni.zave_assignment.ui.theme.DividerColor
import com.joni.zave_assignment.ui.theme.ErrorRed
import com.joni.zave_assignment.ui.theme.OverrideBadge
import com.joni.zave_assignment.ui.theme.Teal500
import com.joni.zave_assignment.ui.theme.TealLight
import com.joni.zave_assignment.ui.theme.TextMuted
import com.joni.zave_assignment.ui.theme.TextPrimary
import com.joni.zave_assignment.ui.theme.ValueGreen

import com.joni.zave_assignment.ui.viewModels.SettingsUiState




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    state: SettingsUiState,
    onRadiusChange: (Int?) -> Unit,
    onAutoLocationChange: (Boolean) -> Unit,
    onSignOut: () -> Unit,
    onBack: () -> Unit
) {
    var showSignOutDialog by remember { mutableStateOf(false) }
    val radiusSliderValue = (state.customRadiusKm ?: state.remoteConfig.defaultRadiusKm).toFloat()
    val isOverridden = state.customRadiusKm != null

    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            title = { Text("Sign out?") },
            text = { Text("You'll need to sign in again to search for stores.") },
            confirmButton = {
                TextButton(onClick = { showSignOutDialog = false; onSignOut() }) {
                    Text("Sign out", color = ErrorRed)
                }
            },
            dismissButton = {
                TextButton(onClick = { showSignOutDialog = false }) { Text("Cancel") }
            }
        )
    }

    Scaffold(
        containerColor = Color(0xFFF7FBF9),
        topBar = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF7FBF9))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(TealLight)
                        .clickable { onBack() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", Modifier.size(18.dp), tint = Color(0xFF05524A))
                }
                Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            // ── ACCOUNT ──────────────────────────────────────────────────
            SettingsSectionLabel("ACCOUNT")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(8.dp))
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.size(40.dp).clip(CircleShape).background(TealLight),
                            contentAlignment = Alignment.Center
                        ) {
                            if (state.photoUrl != null) {
                                AsyncImage(
                                    model = state.photoUrl,
                                    contentDescription = "Avatar",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize().clip(CircleShape)
                                )
                            } else {
                                Text(
                                    text = state.displayName.firstOrNull()?.uppercaseChar()?.toString() ?: "U",
                                    color = Teal500,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                state.displayName.ifBlank { "User" },
                                color = TextPrimary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(state.email, color = TextMuted, fontSize = 13.sp)
                        }
                    }
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = TextMuted
                    )
                }
            }

            // ── LOCATION PREFERENCES ─────────────────────────────────────
            SettingsSectionLabel("LOCATION PREFERENCES")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(8.dp))
            ) {
                // Auto location toggle row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Use location automatically", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                        Text("Requires GPS access", color = TextMuted, fontSize = 13.sp)
                    }
                    // Figma-style toggle
                    Box(
                        modifier = Modifier
                            .width(48.dp)
                            .height(28.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(if (state.useAutoLocation) Teal500 else Color(0xFFD1D5DB))
                            .clickable { onAutoLocationChange(!state.useAutoLocation) }
                            .padding(2.dp),
                        contentAlignment = if (state.useAutoLocation) Alignment.CenterEnd else Alignment.CenterStart
                    ) {
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Color.White)
                        )
                    }
                }

                HorizontalDivider(color = DividerColor)

                // Search radius slider row
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Search radius", color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                                if (isOverridden) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(OverrideBadge)
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text("OVERRIDDEN", color = TextPrimary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                            Text(
                                if (isOverridden) "Manual override applied" else "From Remote Config",
                                color = TextMuted, fontSize = 13.sp
                            )
                        }
                        Text(
                            "${radiusSliderValue.toInt()} km",
                            color = Teal500, fontSize = 16.sp
                        )
                    }

                    Slider(
                        value = radiusSliderValue,
                        onValueChange = { onRadiusChange(it.toInt()) },
                        valueRange = 1f..50f,
                        steps = 48,
                        colors = SliderDefaults.colors(
                            thumbColor = Teal500,
                            activeTrackColor = Teal500,
                            inactiveTrackColor = Color(0xFFF1F5F6)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("1 km", color = TextMuted, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        if (isOverridden) {
                            Text(
                                "Reset to default",
                                color = Teal500,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.clickable { onRadiusChange(null) }
                            )
                        }
                        Text("50 km", color = TextMuted, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }

            // ── REMOTE CONFIG (DEBUG) ─────────────────────────────────────
            SettingsSectionLabel("REMOTE CONFIG (DEBUG)")
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(8.dp))
            ) {
                RemoteConfigRow(
                    key = "default_radius_km",
                    value = "${state.remoteConfig.defaultRadiusKm}",
                    valueColor = OverrideBadge
                )
                HorizontalDivider(color = DividerColor)
                RemoteConfigRow(
                    key = "featured_category",
                    value = state.remoteConfig.featuredCategory,
                    valueColor = ValueGreen
                )
                HorizontalDivider(color = DividerColor)
                RemoteConfigRow(
                    key = "banner_message",
                    value = state.remoteConfig.bannerMessage,
                    valueColor = ValueGreen
                )
            }

            // ── SIGN OUT ──────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .border(BorderStroke(1.dp, DividerColor), RoundedCornerShape(8.dp))
                    .clickable(enabled = !state.isSigningOut) { showSignOutDialog = true }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                if (state.isSigningOut) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp, color = ErrorRed)
                } else {
                    Text(
                        text = "Sign Out",
                        color = ErrorRed,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsSectionLabel(title: String) {
    Text(
        text = title,
        color = TextMuted,
        fontSize = 13.sp,
        letterSpacing = 0.5.sp,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
private fun RemoteConfigRow(key: String, value: String, valueColor: Color) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(key, color = TextMuted, fontSize = 13.sp)
        Text(value, color = valueColor, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}
