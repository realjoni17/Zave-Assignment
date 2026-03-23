package com.joni.zave_assignment.data.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.joni.zave_assignment.data.dao.UserDetailsDao
import com.joni.zave_assignment.data.entities.UserDetails
import com.joni.zave_assignment.domain.repositories.FirebaseAuthRepository
import com.joni.zave_assignment.utils.Result
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepositoryImpl(private val auth : FirebaseAuth, private val userDetailsDao: UserDetailsDao) : FirebaseAuthRepository {

    override val currentUser: FirebaseUser?
        get() = auth.currentUser

    override val isLoggedIn : Boolean
        get() = auth.currentUser != null

    override suspend fun signInWithGoogle(idToken: String) : Result<Unit> = try{

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val result = auth.signInWithCredential(credential).await()
        val user = result.user ?: return Result.Error("Sign-in failed: no user returned")
        userDetailsDao.upsert(
            UserDetails(
                email = user.email ?: "",
                displayName = user.displayName ?: "",
                photoUrl = user.photoUrl?.toString(),
                lastKnownLat = null,
                lastKnownLng = null,
                customRadiusKm = null
            )
        )
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e.message ?: "Authentication failed", e)
    }

    override suspend fun signOut() {
        auth.signOut()
        userDetailsDao.clear()
    }
}