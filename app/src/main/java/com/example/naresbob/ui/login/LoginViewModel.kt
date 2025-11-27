package com.example.naresbob.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.naresbob.domain.usecase.GetTokenUseCase
import com.example.naresbob.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginUiState(isLoading = true)

            val result = loginUseCase(username, password)

            result.onSuccess { user ->
                _state.value = LoginUiState(
                    isLoading = false,
                    success = true,
                    message = "Selamat datang, ${user.name}"
                )
            }

            result.onFailure { e ->
                _state.value = LoginUiState(
                    isLoading = false,
                    success = false,
                    message = e.message ?: "Terjadi kesalahan"
                )
            }
        }
    }
}