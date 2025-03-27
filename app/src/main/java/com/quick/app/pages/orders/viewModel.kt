package com.quick.app.pages.orders

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import com.quick.app.models.Order
import kotlinx.coroutines.launch

class OrdersViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    // 只有第一次打开时才会更新
    private val type = checkNotNull(savedStateHandle["type"])
    val currIndex = mutableIntStateOf(0)
    val list = mutableStateOf<List<Order>>(emptyList())
    val uiState = mutableStateOf<LoadingState>(LoadingState.Loading)

    init {
        loadData(mapOf())
    }

    fun handleSwitchType(index: Int) {
        if (currIndex.intValue == index) return
        currIndex.intValue = index
    }

    private fun loadData(params: Map<String, String>) {
        viewModelScope.launch {
            uiState.value = LoadingState.Loading
            try {
                val res = HomeApi.orders(params)
                if (res.status != 0) {
                    uiState.value = LoadingState.Error401(res.message ?: "登录失效")
                    return@launch
                }
                list.value = res.data?.list ?: emptyList()
                uiState.value = LoadingState.Success
            } catch (e: Exception) {
                uiState.value = LoadingState.Error(e.message ?: "服务异常")
            }
        }
    }
}