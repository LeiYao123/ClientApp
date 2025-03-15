package com.quick.app

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel

// 1. 定义全局 ViewModel, 全局数据中心
class AppViewModel : ViewModel() {
    // 全局共享状态
    val appNum = mutableIntStateOf(0)


    init {
        Log.d("MeViewModel", "app 我执行了")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MeViewModel", "app 我被销毁了")
    }
}