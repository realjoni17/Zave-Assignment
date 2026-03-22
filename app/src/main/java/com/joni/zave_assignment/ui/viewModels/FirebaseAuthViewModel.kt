package com.joni.zave_assignment.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joni.zave_assignment.domain.repositories.FirebaseAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class FirebaseAuthViewModel(private val repository: FirebaseAuthRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(false)
    val uiState = _uiState.asStateFlow()

    fun signInWithGoogle(idToken : String){
        viewModelScope.launch {
         val result = repository.signInWithGoogle(idToken)
         _uiState.value
        }
    }
}