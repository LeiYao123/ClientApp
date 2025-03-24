package com.quick.app.pages.home

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import com.quick.app.models.ProductModel
import kotlinx.coroutines.launch

class HomeViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    // 只有第一次打开时才会更新
    private val id: String? = savedStateHandle["id"]
    val count = mutableIntStateOf(0)
    var datum = mutableStateOf(emptyList<ProductModel>())
    val listUiState = mutableStateOf<HomeListUiState>(HomeListUiState.Loading)
    val detailUiState = mutableStateOf<DetailUiState>(DetailUiState.Loading)

    // bottom sheet 弹窗
    val showBottomDialog = mutableStateOf(false)

    init {
        Log.d("homeViewModel", "HomeViewModel 我执行了 --id --> $id")
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            listUiState.value = HomeListUiState.Loading
            try {
                val res = HomeApi.getProducts()
                datum.value = res.data?.list ?: emptyList()
                listUiState.value = HomeListUiState.Success
            } catch (e: Exception) {
                listUiState.value = HomeListUiState.Error(e)
            }
        }
    }

    fun loadDetail(id: String) {
        viewModelScope.launch {
            detailUiState.value = DetailUiState.Loading
            try {
                val res = HomeApi.productDetail(id)
                detailUiState.value = DetailUiState.Success(res.data!!)
            } catch (e: Exception) {
                detailUiState.value = DetailUiState.Error(e)
            }
        }
    }
}
