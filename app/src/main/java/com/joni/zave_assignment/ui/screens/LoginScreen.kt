
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.joni.zave_assignment.BuildConfig
import com.joni.zave_assignment.R
import com.joni.zave_assignment.ui.theme.Teal500
import com.joni.zave_assignment.ui.theme.TextMuted
import com.joni.zave_assignment.ui.theme.TextPrimary
import com.joni.zave_assignment.ui.viewModels.AuthUiState


@Composable
fun AuthScreen(
    state: AuthUiState,
    onGoogleSignIn: (String) -> Unit,
    onSignInSuccess: () -> Unit,
    onClearError: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(state.isSignedIn) {
        if (state.isSignedIn) onSignInSuccess()
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { onGoogleSignIn(it) }
            } catch (e: ApiException) {
                Log.d("TAG", "AuthScreen: ${e.message}")
            }
        }
    }

    fun launchSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.web)
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(context, gso)
        client.signOut().addOnCompleteListener {
            googleSignInLauncher.launch(client.signInIntent)
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7FBF9))
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 220.dp)
                .padding(horizontal = 24.dp)
        ) {

            Surface(
                shape = RoundedCornerShape(12.dp),
                shadowElevation = 32.dp,
                modifier = Modifier.size(160.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFE6FFF6)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Z",
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Bold,
                        color = Teal500
                    )
                }
            }

            Spacer(Modifier.height(40.dp))

            Text(
                text = "Store Finder",
                color = TextPrimary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.5).sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = "Discover nearby shops, browse\nproducts, and navigate with ease.",
                color = TextMuted,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 40.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (!state.isLoading) Teal500 else Teal500.copy(alpha = 0.6f))
                    .clickable(enabled = !state.isLoading) { launchSignIn() }
                    .padding(16.dp)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.White
                    )
                }
                Text(
                    text = if (state.isLoading) "Signing in…" else "Sign in with Google",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            }


            Text(
                textAlign = TextAlign.Center,
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = TextMuted, fontSize = 13.sp)) {
                        append("By signing in, you agree to our ")
                    }
                    withStyle(SpanStyle(color = Teal500, fontSize = 13.sp)) { append("Terms") }
                    withStyle(SpanStyle(color = TextMuted, fontSize = 13.sp)) { append(" & ") }
                    withStyle(SpanStyle(color = Teal500, fontSize = 13.sp)) { append("Privacy Policy") }
                    withStyle(SpanStyle(color = TextMuted, fontSize = 13.sp)) { append(".") }
                },
                modifier = Modifier.fillMaxWidth()
            )


            AnimatedVisibility(visible = state.error != null) {
                state.error?.let { error ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFFFEBEB))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = error,
                            color = Color(0xFFEF4444),
                            fontSize = 13.sp,
                            modifier = Modifier.weight(1f)
                        )
                        TextButton(
                            onClick = onClearError,
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Dismiss", color = Color(0xFFEF4444), fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}
