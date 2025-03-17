package com.quick.app.pages.home

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import com.quick.app.models.ProductModel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val count = mutableIntStateOf(0)
    var datum = mutableStateOf(emptyList<ProductModel>())

    init {
        Log.d("homeViewModel", "HomeViewModel 我执行了")
        viewModelScope.launch {
            try {
                val res = HomeApi.getProducts()
                datum.value = res.data?.list ?: emptyList()
            } catch (e: Exception) {
                Log.e("response", e.toString())
            }
        }
    }
}
