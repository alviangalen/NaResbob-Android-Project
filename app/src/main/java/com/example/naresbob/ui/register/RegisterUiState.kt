package com.example.naresbob.ui.register

data class RegisterUiState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val success: Boolean = false
)