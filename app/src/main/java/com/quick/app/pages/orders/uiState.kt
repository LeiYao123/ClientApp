package com.quick.app.pages.orders

sealed interface LoadingState {
    data object Success : LoadingState
    data object Loading : LoadingState
    data class Error(val data: String) : LoadingState
    data class Error401(val data: String) : LoadingState
    data object NoMoreData : LoadingState
}