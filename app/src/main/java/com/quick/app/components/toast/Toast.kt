package com.quick.app.components.toast

import android.app.Application
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class ToastType { SUCCESS, ERROR, WARNING, INFO }
enum class ToastPosition { TOP, CENTER, BOTTOM }

const val DURATION = 2000L

data class ToastData(
    val message: String,
    val type: ToastType = ToastType.INFO,
    val duration: Long = DURATION,
    val position: ToastPosition = ToastPosition.TOP,
    val id: Long = 0, // 唯一标识
)

class ToastManager(application: Application) : AndroidViewModel(application) {
    private val _toasts = MutableStateFlow<List<ToastData>>(emptyList())
    val toasts = _toasts.asStateFlow()

    // 显示一个新的 toast
    fun show(toast: ToastData) {
        val newToast = toast.copy(id = System.currentTimeMillis()) // 确保每个 Toast 唯一
        _toasts.update { it + newToast }

        viewModelScope.launch {
            delay(toast.duration) // 等待指定时间后消失
            dismiss(newToast.id) // 消失逻辑
        }
    }

    // 手动关闭一个指定的 toast
    fun dismiss(id: Long) {
        _toasts.update { it.filterNot { toast -> toast.id == id } }
    }
}

object ToastCenter {
    private var vm: ToastManager? = null

    // 初始化
    fun init(viewModel: ToastManager) {
        vm = viewModel
    }

    // 显示 toast
    fun show(
        message: String,
        type: ToastType = ToastType.INFO,
        duration: Long = DURATION,
        position: ToastPosition = ToastPosition.TOP,
    ) {
        vm?.show(ToastData(message, type, duration, position))
    }

    // 手动关闭 toast
    fun dismiss(id: Long) = vm?.dismiss(id)
}

@Composable
fun ToastHost(viewModel: ToastManager = viewModel()) {
    val toasts by viewModel.toasts.collectAsState()

    LaunchedEffect(Unit) {
        ToastCenter.init(viewModel)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        toasts.forEachIndexed { index, toast ->
            Box(
                modifier = Modifier
                    .align(getAlignment(toast.position))
                    .padding(top = (60 + index * 70).dp) // 防止重叠
            ) {
                AnimatedToast(data = toast, onDismiss = { viewModel.dismiss(toast.id) })
            }
        }
    }
}


fun getAlignment(position: ToastPosition): Alignment = when (position) {
    ToastPosition.TOP -> Alignment.TopCenter
    ToastPosition.CENTER -> Alignment.Center
    ToastPosition.BOTTOM -> Alignment.BottomCenter
}

@Composable
private fun AnimatedToast(data: ToastData, onDismiss: () -> Unit) {
    val bgColor = when (data.type) {
        ToastType.SUCCESS -> Color(0xFF4CAF50)
        ToastType.ERROR -> Color(0xFFF44336)
        ToastType.WARNING -> Color(0xFFFFC107)
        ToastType.INFO -> Color(0xFF2196F3)
    }
    val icon = when (data.type) {
        ToastType.SUCCESS -> Icons.Default.CheckCircle
        ToastType.ERROR -> Icons.Default.Error
        ToastType.WARNING -> Icons.Default.Warning
        ToastType.INFO -> Icons.Default.Info
    }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }

    val offsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else (-100).dp,
        animationSpec = tween(300),
        label = "toast_anim"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .offset { IntOffset(0, offsetY.roundToPx()) }
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        Row(
            modifier = Modifier
                .background(bgColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = data.message,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "关闭",
                tint = Color.White,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { onDismiss() }
            )
        }
    }
}
