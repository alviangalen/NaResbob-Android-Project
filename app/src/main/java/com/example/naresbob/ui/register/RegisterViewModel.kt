package com.example.naresbob.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naresbob.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state

    fun register(username: String, fullname: String, dateOfbirth: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterUiState(isLoading = true)

            val result = registerUseCase(username, fullname, dateOfbirth, password)

            result.onSuccess { user ->
                _state.value = RegisterUiState(
                    isLoading = false,
                    success = true,
                    message = "Daftar Berhasil"
                )
            }

            result.onFailure { e ->
                _state.value = RegisterUiState(
                    isLoading = false,
                    success = false,
                    message = e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}