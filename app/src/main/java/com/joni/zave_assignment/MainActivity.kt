package com.joni.zave_assignment

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.joni.zave_assignment.ui.navigation.ZaveNavHost
import com.joni.zave_assignment.ui.theme.ZaveassignmentTheme
import com.joni.zave_assignment.ui.viewModels.FirebaseAuthViewModel
import com.joni.zave_assignment.ui.viewModels.SearchScreenViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {




    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
         val viewModel = viewModels<SearchScreenViewModel>()

        enableEdgeToEdge()
        setContent {
            ZaveassignmentTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ZaveNavHost()
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: FirebaseAuthViewModel = koinViewModel()) {
    val context = LocalContext.current

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                account.idToken?.let { viewModel.signInWithGoogle(it) }
                Log.d("TAG", "Greeting: ${account.idToken}")
            } catch (e: ApiException) {
                // Handled via state
                Log.d("TAG", "Greeting: ${"jio"}")
            }
        }
    }

    fun launchSignIn() {
        Log.d("TAG", "launchSignIn: ${BuildConfig.web}")
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

            .requestIdToken(BuildConfig.web)
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(context, gso)
        client.signOut().addOnCompleteListener {
            googleSignInLauncher.launch(client.signInIntent)
        }.addOnFailureListener { p0->
            Log.d("TAG", "launchSignIn: $p0")
        }

    }



    Button(
        onClick = { launchSignIn() },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),

    ) {

            Text("Continue with Google", style = MaterialTheme.typography.labelLarge)

    }



}

