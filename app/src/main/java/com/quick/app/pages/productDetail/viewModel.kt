package com.quick.app.pages.productDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    //    private val _id = savedStateHandle.getStateFlow("id", "")
    private val id: String = checkNotNull(savedStateHandle["id"])
//    private var _datum = MutableStateFlow<ProductModel?>(null)
//    val datum = _datum

    private val _uiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val uiState: StateFlow<DetailUiState> = _uiState

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val res = HomeApi.productDetail(id)
                _uiState.value = DetailUiState.Success(res.data!!)
            } catch (e: Exception) {
                _uiState.value = DetailUiState.Error(e)
            }
        }

    }
}