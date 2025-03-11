package com.quick.app.pages.loginAccount


sealed class LoginUiState {
    data object None : LoginUiState()
    data object Success : LoginUiState()
    data class Error(val error: Throwable) : LoginUiState()
}