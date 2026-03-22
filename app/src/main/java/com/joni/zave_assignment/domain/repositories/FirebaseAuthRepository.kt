package com.joni.zave_assignment.domain.repositories

import com.google.firebase.auth.FirebaseUser
import com.joni.zave_assignment.utils.Result

interface FirebaseAuthRepository {

    val currentUser: FirebaseUser?

    val isLoggedIn: Boolean

    suspend fun signInWithGoogle(idToken: String) : Result<Unit>

    suspend fun signOut()
}