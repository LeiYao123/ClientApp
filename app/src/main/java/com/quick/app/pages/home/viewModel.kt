package com.quick.app.pages.home

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import com.quick.app.models.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private var _datum = MutableStateFlow<List<ProductModel>>(emptyList())
    val datum: StateFlow<List<ProductModel>> = _datum
    val count = mutableIntStateOf(0)

    init {
        Log.d("homeViewModel", "HomeViewModel 我执行了")
        viewModelScope.launch {
            try {
                val res = HomeApi.getProducts()
                _datum.value = res.data?.list ?: emptyList()
            } catch (e: Exception) {
                Log.e("response", e.toString())
            }
        }
    }
}
