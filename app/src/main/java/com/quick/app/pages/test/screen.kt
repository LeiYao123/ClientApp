package com.quick.app.pages.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.quick.app.pages.test.comps.DeviceInfoPage
import com.quick.app.pages.test.comps.HardwareInfo


@Composable
fun TestScreenRoute() {
    Scaffold {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxSize()
                .padding(it),
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Surface(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth()
                ) {
                    DeviceInfoPage()
                }
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(16.dp)
                        .background(Color.Gray, shape = RoundedCornerShape(24.dp))
//                        .background(Color.Gray) // 设置背景颜色（不带圆角）
//                        .clip(RoundedCornerShape(16.dp)) // 添加圆角
//                        .background(Color.Blue) // 设置圆角背景颜色

                )
                Surface(
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 20.dp)
                        .fillMaxWidth()
                ) {
                    HardwareInfo()
                }
            }
        }
    }
}
