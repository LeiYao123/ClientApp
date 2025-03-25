package com.quick.app.pages.orders

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import com.quick.app.config.Constant
import com.quick.app.models.Order
import kotlinx.coroutines.launch

class OrdersViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    // 只有第一次打开时才会更新
    private val type = checkNotNull(savedStateHandle["type"])
    val currIndex = mutableIntStateOf(0);
    val list = mutableStateOf<List<Order>>(emptyList())
    val uiState = mutableStateOf<LoadingState>(LoadingState.None)

    init {
        loadData(mapOf())
    }

    private val indicatorValues = intArrayOf(
        Constant.VALUE_NO,
        Constant.WAIT_PAY,
        Constant.WAIT_RECEIVED,
        Constant.WAIT_COMMENT
    )

    fun handleSwitchType(index: Int) {
        if (currIndex.intValue == index) return
        currIndex.intValue = index
    }

    private fun loadData(params: Map<String, String>) {
        viewModelScope.launch {
            uiState.value = LoadingState.Loading
            try {
                val res = HomeApi.orders(params)
                if (res.status != 0) uiState.value = LoadingState.Error(res.message ?: "服务异常")
                list.value = res.data?.list ?: emptyList()
                Log.d("OrdersViewModel", "list: ${list.value}")
            } catch (e: Exception) {
                Log.d("OrdersViewModel", "$e")
                uiState.value = LoadingState.Error(e.message ?: "服务异常")
            }
        }
    }
}