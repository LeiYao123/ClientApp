package com.quick.app.pages.loginAccount

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quick.app.api.HomeApi
import com.quick.app.components.toast.Toast
import com.quick.app.data.UserUtil
import com.quick.app.models.LoginParams
import kotlinx.coroutines.launch

class LoginAccountViewModel : ViewModel() {
    var phone by mutableStateOf("13141111222")
    var pwd by mutableStateOf("ixueaedu")
    val uiState = mutableStateOf<LoginUiState>(LoginUiState.None)

    fun login() {
        if (phone.isEmpty() || pwd.isEmpty()) {
            uiState.value = LoginUiState.Error(Throwable("请输入手机号和密码"))
            Toast.show("请输入手机号和密码")
            return
        }
        val params = LoginParams(phone = phone, password = pwd)
        viewModelScope.launch {
            try {
                val res = HomeApi.login(params)
                if (res.status != 0) {
                    "请求失败 ${res.message}"
                    uiState.value = LoginUiState.Error(Throwable(res.message))
                    return@launch
                }
                Toast.show("请求成功")
                UserUtil.saveSession(res.data)
                // 成功跳转首页
                uiState.value = LoginUiState.Success
            } catch (e: Exception) {
                uiState.value = LoginUiState.Error(e)
                Toast.show("${e.message}")
            }
        }
    }
}