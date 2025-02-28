package com.quick.app.feature.splash

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val controller = LocalNavController.current
    val tl by viewModel.timeLeft.collectAsState()
    Log.d("Route", "SplashRoute -- tl --$tl")

    if (tl == 0) {
        Log.d("Route", "navigate 跳转")
        LaunchedEffect(Unit) {
            controller.navigate(PageRoutes.GuideParams("3456789").route)
        }
    }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            "time left: ${tl}s",
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp, end = 50.dp)
        )
        Image(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 150.dp),
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
}

@Composable
fun CopyRight() {
    val controller = LocalNavController.current
    val vm = viewModel<SplashViewModel>()
    val tl by vm.timeLeft.collectAsState()
    Text(
        "Copy Right 2024 Reversed $tl",
        color = MaterialTheme.colorScheme.outline,
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(bottom = 70.dp)
            .clickable {
                controller.navigate(PageRoutes.GuideParams("3456").route)
            },
    )
}


@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun SplashRoutePreview() {
    PreviewContent()
}