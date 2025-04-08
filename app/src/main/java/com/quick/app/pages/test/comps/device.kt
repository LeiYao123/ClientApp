package com.quick.app.pages.test.comps

import android.content.res.Configuration
import android.os.Build
import android.util.DisplayMetrics
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

@Composable
fun DeviceInfoPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "LocalConfiguration - 获取当前设备的一些基础「配置参数」",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
        val cfg = LocalConfiguration.current
        val screenWidthDp = cfg.screenWidthDp
        val screenHeightDp = cfg.screenHeightDp
        val orientation = cfg.orientation

        InfoItem("尺寸 (dp)", "$screenWidthDp * $screenHeightDp")
        InfoItem("最小宽度 (dp)", "${cfg.smallestScreenWidthDp}")
        InfoItem(
            "densityDpi",
            "${cfg.densityDpi}  (含义: dpi（dots per inch）Configuration 类（Compose 封装的 Android 原生)"
        )
        InfoItem("字体缩放比例", "${cfg.fontScale}")
        InfoItem("方向", if (orientation == Configuration.ORIENTATION_PORTRAIT) "竖屏" else "横屏")

        Text(
            "LocalDensity 是 Compose 中用于 dp ↔ px 转换的工具",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
        val density = LocalDensity.current
        val screenWidthPx = with(density) { screenWidthDp.dp.toPx().roundToInt() }
        val screenHeightPx = with(density) { screenHeightDp.dp.toPx().roundToInt() }

        InfoItem("density", "${density.density}")
        InfoItem("字体缩放倍数(compose)", "${density.fontScale}")
        InfoItem(
            "尺寸 (px)",
            "$screenWidthPx * $screenHeightPx  (通过计算得来 screenWidthDp.dp.toPx().roundToInt())"
        )


    }
}


@Composable
fun HardwareInfo() {
    val context = LocalContext.current
    val metrics: DisplayMetrics = context.resources.displayMetrics

    val screenInches = remember {
        val widthInches = metrics.widthPixels / metrics.xdpi
        val heightInches = metrics.heightPixels / metrics.ydpi
        sqrt(widthInches.pow(2) + heightInches.pow(2))
    }


    val deviceInfo = listOf(
        "系统版本 (API)" to "${Build.VERSION.SDK_INT}",
        "系统版本名" to Build.VERSION.RELEASE,
        "设备品牌" to Build.BRAND,
        "设备型号" to Build.MODEL,
        "设备名称" to Build.DEVICE,
        "产品名" to Build.PRODUCT,
        "硬件信息" to Build.HARDWARE,
        "主板" to Build.BOARD
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "context.resources.displayMetrics Android 原生方式，获取当前屏幕的 像素级参数",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))

        InfoItem("Density", "${metrics.density}")
        InfoItem("DPI", "${metrics.densityDpi}")
        InfoItem("宽高(px)", "${metrics.widthPixels} * ${metrics.heightPixels}")
        InfoItem("xdpi", "${metrics.xdpi}")
        InfoItem("ydpi", "${metrics.ydpi}")
        InfoItem("屏幕尺寸", String.format("%.2f 英寸", screenInches))
        deviceInfo.forEach { (label, value) -> InfoItem(label, value) }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        Text(label, fontWeight = FontWeight.Medium, modifier = Modifier.width(200.dp))
        Text(value)
    }
    Spacer(Modifier.height(8.dp))
}
