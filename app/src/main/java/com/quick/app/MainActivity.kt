package com.quick.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.quick.app.feature.splash.SplashRoute
import com.quick.app.route.IndexRoute
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
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    println(it)
                    IndexRoute()
                }
            }
        }
    }
}

