package com.quick.app.feature.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quick.app.PreviewContent
import com.quick.app.R
import com.quick.app.route.LocalNavController
import com.quick.app.route.PageRoutes


@Composable
fun SplashRoute(
    modifier: Modifier = Modifier,
    viewModel: SplashViewModel = viewModel(),
) {

    val cfg = LocalConfiguration.current


    val controller = LocalNavController.current
    val tl by viewModel.timeLeft.collectAsState()


    fun toNextPage(id: String) {
        controller.navigate(PageRoutes.GuideParams(id).route)
        viewModel.stopCountDown()
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Surface(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxWidth(),
            color = Color.Red.copy(alpha = 0.5f),
        ) {
            Column {
                Text(text = "屏幕宽度: ${cfg.screenWidthDp} dp")
                Text(text = "屏幕高度: ${cfg.screenHeightDp} dp")
                Text(text = "最小屏幕宽度: ${cfg.smallestScreenWidthDp} dp")
                Text(text = "字体缩放比例: ${cfg.fontScale}")
                Text(text = "屏幕密度 (dpi): ${cfg.densityDpi}")
                Text(text = "LocalDensity: ${LocalDensity.current.density}")

            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp),
            colors = ButtonDefaults.buttonColors().copy(containerColor = Color.Red),
            contentPadding = PaddingValues(0.dp),
            onClick = { toNextPage("123456") }
        ) { Text("${tl}s") }
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 180.dp),
            painter = painterResource(id = R.drawable.splash_banner),
            contentDescription = "App Logo"
        )
        Image(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(200.dp)
                .padding(bottom = 90.dp),
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = "App Logo"
        )

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            CopyRight()
        }
    }

    if (tl == 0) {
        Log.d("Route", "navigate 跳转")
        LaunchedEffect(Unit) {
            toNextPage("3456789")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            Log.d("Route", "Splash onDispose -->")
        }
    }
}

@Composable
fun CopyRight() {
    val vm = viewModel<SplashViewModel>()
    val tl by vm.timeLeft.collectAsState()
    Text(
        "Copy Right 2024 Reversed $tl",
        color = MaterialTheme.colorScheme.outline,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = 70.dp)
    )
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun SplashRoutePreview() {
    PreviewContent()
}