package com.quick.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.quick.app.route.AppRoute
import com.quick.app.ui.theme.ClientAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        调整状态栏文字颜色
//        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.dark(android.graphics.Color.TRANSPARENT)
//        )
        setContent {
            ClientAppTheme {
                Surface(color = Color.White) { AppRoute() }
            }
        }
    }
}


// 预览函数模板
@Composable
fun PreviewContent(routeName: String? = null, content: (@Composable () -> Unit)? = null) {
    ClientAppTheme {
        Surface(color = Color.White) {
            content ?: AppRoute(routeName)
        }
    }
}