package com.quick.app.pages.web

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class WebViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val uiState: StateFlow<WebUiState> =
        savedStateHandle.getStateFlow(key = "web_param", initialValue = "")
//            .map { WebUiState.Success(WebParam(uri = "https://juejin.cn")) }
            .map { WebUiState.Success(Gson().fromJson(it, WebParam::class.java)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = WebUiState.Loading,
            )
}