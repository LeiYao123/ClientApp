package com.quick.app.models.network

data class NetworkRes<T>(
    val data: T? = null,
    val status: Int = 0,
    val message: String? = null,
)
