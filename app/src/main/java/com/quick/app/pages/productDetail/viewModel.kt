package com.quick.app.pages.productDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel


class DetailViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    //    private val _id = savedStateHandle.getStateFlow("id", "")
    val id = checkNotNull(savedStateHandle["id"])
}