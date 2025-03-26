package com.quick.app.pages.web

import com.google.gson.Gson
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


data class WebParam(
    val uri: String? = null,
    val content: String? = null,
)

sealed interface WebUiState {
    data class Success(val data: WebParam) : WebUiState
    data object Loading : WebUiState
}

fun createWebParam(uri: String? = null, content: String? = null): String {
    val param = WebParam(uri = uri, content = null)
    val json = Gson().toJson(param)  // JSON 序列化
    val encodedJson = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
    return "web/$encodedJson"
}