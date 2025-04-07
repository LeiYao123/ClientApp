package com.quick.app.pages.index

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.route.PageRoutes
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


val pagesArr = listOf(
    PageRoutes.Home.route,
    PageRoutes.Video.route,
    PageRoutes.Me.route
)


class IndexViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val page: StateFlow<String> =
        savedStateHandle.getStateFlow(key = "page", initialValue = "")
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PageRoutes.Home.route,
            )

    val page2 = savedStateHandle.get<String>("page")

    init {
        Log.d("page-->", "${page.value} , $page2")
    }

    var currTab = mutableStateOf(page2!!)
}